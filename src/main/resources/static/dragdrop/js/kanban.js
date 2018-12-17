var grids = document.querySelectorAll('.grid');
var qtdGridTotal = grids.length;
var gridDiaTurno = [];

//Inicia e controla cada grid
grids.forEach(function (element, index) {
  gridDiaTurno[index] = new Muuri('.grid-' + (index), {
      items: '.item',
      layoutDuration: 400,
      dragEnabled: true,
      dragContainer: document.body,
      dragSort: getAllGrids,
      dragSortInterval: 0,
      dragContainer: document.body,
      dragReleaseDuration: 400,
      dragReleaseEasing: 'ease'
    })
    .on('dragStart', function (item) {
      //Durante o arrasto, impede que o elemento fique em maior tamanho
      item.getElement().style.width = item.getWidth() + 'px';
      item.getElement().style.height = item.getHeight() + 'px';
    })
    .on('dragReleaseEnd', function (item) {
      var ordemGrid = [];
      var gridElem = item.getGrid(); //retorna o grid do elemento arrastado
      var items = gridElem.getItems(); //retorna um array todos os items do grid
      var turno;
      var dia;
      for (let i = 0; i < items.length; i++) {
        let valorElem = items[i].getElement().children[0].children[0].getAttribute('value');
        let primeiro = valorElem.indexOf('/');
        let crm = valorElem.slice(0, primeiro);
        ordemGrid.push(crm);

        if (i == 0) { //executar somente uma vez
          let segundo = valorElem.lastIndexOf('/');
          turno = valorElem.slice(primeiro + 1, segundo);
          dia = valorElem.slice(segundo + 1, valorElem.length);
        }
      }

      var crms = {
        'ordemcrm': ordemGrid
      };

      //chama o ajax 
      $.ajax({
          url: endereco + '/escaladragdrop/ordenarmedicos/' + turno + '/' + dia,
          type: 'POST',
          contentType: 'application/json',
          data: JSON.stringify(ordemGrid),
        })
        .done(function () {
          console.log("ordem atualizada");
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
          alert("Desculpe! Houve um erro ao atualizar a ordem: " + jqXHR.status);
        });
    });
});

// Retorna todos os drags
function getAllGrids(item) {
  return gridDiaTurno.slice(0, qtdGridTotal);
}

//Adiciona o elemento ao grid
function adicionaItem(nome, crm) {
  let func = $(':checked').attr('name');
  if (func == undefined) {
    func = 'semfuncao';
  }

  //tudoDoClick e diaDoclick são variáveis globais
  var json = {
    "crm": crm,
    "func": func,
    "turno": turnoDoClick,
    "dia": diaDoClick
  };
  var value = crm + '/' + turnoDoClick + '/' + diaDoClick;

  //recupera o day desta data onde foi clicado para adicionar o médico
  var dayClick = new Date(year, month, diaDoClick, 10, 00, 00).getDay();

  //chama o ajax 
  $.ajax({
      type: 'POST',
      url: endereco + '/escaladragdrop/salvarmedico/' + crm + '/' + func + '/' + turnoDoClick + '/' + diaDoClick,
    })
    .done(function (msg) {
      //Se o medico adicionado tiver funcao ele será colocado no inicio
      if (func != 'semfuncao') {
        var elem = generateElement(nome + ' ' + func.toUpperCase(), value, turnoDoClick, dayClick);
        gridDiaTurno[gridIndex].add(elem, {
          index: 0
        });
      }
      //Se o medico adicionado não tiver funcao ele será colocado no fim
      else {
        var elem = generateElement(nome, value, turnoDoClick, dayClick);
        gridDiaTurno[gridIndex].add(elem);
      }

      //Torna invisível o nome do médico adicionado e a sua div funcao
      $('#' + crm + '-func').hide();
      $('#' + crm).hide();

      //Desmacar o checkbox do médico adicionado
      $(':checked').prop('checked', false)

      //Torna invisível todas as funções, baseado na função marcada
      let checkboxs = $(':checkbox');
      for (let i = 0; i < checkboxs.length; i++) {
        if (checkboxs[i].name == func) {
          checkboxs[i].style.display = 'none';
        }
      }
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      alert("Desculpe! Houve um erro ao adicionar o médico: " + jqXHR.status);
    });
}

//Gera um elemento
function generateElement(nome, value, turno, day) {
  var itemElem = document.createElement('div');
  var itemTemplate;
  let classe = 'item-content';

  console.log(day);
  if (day == 0 || day == 6) {
    classe = 'item-content feriado';
  }else{
    //verifica se a data é um feriado
    //se positivo, adiciona a classe feriado
    for (let i = 0; i < feriados.length; i++) {
      let dataFeriado = new Date(feriados[i].data);
      if (diaDoClick == dataFeriado.getDate()) {
        classe = 'item-content feriado';
      }
    }
  }

  switch (turno) {
    case 'manha':
      itemTemplate = '<div class="item"><div class="'+classe+'"><span value=' + value + '>' + nome + '</span></div></div>';
      break;
    case 'tarde':
      itemTemplate = '<div class="item"><div class="tardeItem '+classe+'"><span value=' + value + '>' + nome + '</span></div></div>';
      break;
    case 'noite':
      itemTemplate = '<div class="item"><div class="noiteItem '+classe+'"><span value=' + value + '>' + nome + '</span></div></div>';
      break;
  }

  itemElem.innerHTML = itemTemplate;
  return itemElem.firstChild;
}

//exclui o item do grid
function removeItem(index) {
  var elem = gridDiaTurno[index].getItems(0)[0];
  let valueReq = elem.getElement().children[0].children[0].getAttribute('value');

  $.ajax({
      url: endereco + '/escaladragdrop/excluirmedico/' + valueReq,
      type: 'DELETE'
    })
    .done(function () {
      gridDiaTurno[index].remove(elem, {
        removeElements: true
      });
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      alert("Desculpe! Houve um erro: " + jqXHR.status);
    });

}