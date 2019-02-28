var tabelas = document.querySelectorAll('table');
var diasSemana = ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'];

// Preenche as datas no cabeçalho das tabelas
DiasDatasSemanas(0);

function DiasDatasSemanas(cont) {
  var header_sem = tabelas[cont].querySelectorAll('th');
  datas.forEach(element => {
    var data = new Date(element);
    addHeader(data, header_sem);
    if (data.getDay() == 6 && data.getDate()) {
      try {
        header_sem = tabelas[++cont].querySelectorAll('th');
      } catch (e) {
        throw "O calendário já foi preenchido";
      }
    }
  });
}
//Função de auxilio para preencer as datas no cabeçalho das tabelas
function addHeader(data, header_sem) {
  header_sem.forEach(element => {
    let dt = data.getDay();
    if (element.textContent == diasSemana[dt]) {
      element.innerHTML = diasSemana[dt] + "<span class='float-right'>" + data.getDate() + "</span>";
      return;
    }
  });
}

//Apaga a ultima tabela se não houver dias do mês
apagaTabela();

function apagaTabela() {
  let cont = 0;
  while (cont < tabelas.length) {
    let header = tabelas[cont].querySelectorAll('th');
    let listIndexVazias = [];
    for (let index = 0; index < header.length; index++) {
      let filho = header[index].children[0];
      if (filho == undefined) {
        listIndexVazias.push(index);
      }
    }

    let celsManha = tabelas[cont].querySelectorAll('.manha');
    let celsTarde = tabelas[cont].querySelectorAll('.tarde');
    let celsNoite = tabelas[cont].querySelectorAll('.noite');
    for (let index = 0; index < listIndexVazias.length; index++) {
      let num = listIndexVazias[index];
      if (num > 0 && num < 8) {
        celsManha[num - 1].className = '';
        celsTarde[num - 1].className = '';
        celsNoite[num - 1].className = '';
      }
    }
    //Apaga a última tabela se não tiver nenhum dia
    if (listIndexVazias.length == 11) {
      tabelas[cont].style.display = 'none';
    }
    ++cont;
  }
}

//Insere a data como identificador em cada celula
addIdentificador();

function addIdentificador() {
  let manha = document.querySelectorAll('.manha');
  let tarde = document.querySelectorAll('.tarde');
  let noite = document.querySelectorAll('.noite');
  datas.forEach(element => {
    var data = new Date(element);
    manha[data.getDate() - 1].id = 'm' + data.getDate();
    tarde[data.getDate() - 1].id = 't' + data.getDate();
    noite[data.getDate() - 1].id = 'n' + data.getDate();
  });
}

//Insere os medicos M5 em cada celula
addMedicoM5();

function addMedicoM5() {
  medsM5.forEach(med => {
    let dataMed = new Date(med.data);
    let dataReq = dataMed.getDate();
    let turnoMed = med.turno;
    let classe = 'item-med';

    if (dataMed.getDay() == 0 || dataMed.getDay() == 6) {
      classe = 'item-med feriado';
    }

    switch (turnoMed) {
      case 'manha':
        $('#m' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome + " M5" +
          '</span></div></div>');
        break;
      case 'tarde':
        $('#t' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="tardeItem ' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome + " M5" +
          '</span></div></div>');
        break;
      case 'noite':
        $('#n' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="item-content ' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome + " M5" +
          '</span></div></div>');
        break;
    }
  });
}

//Insere os medicos HRO em cada celula
addMedicoHro();

function addMedicoHro() {
  medsHro.forEach(med => {
    let dataMed = new Date(med.data);
    let dataReq = dataMed.getDate();
    let turnoMed = med.turno;
    let classe = 'item-med';

    if (dataMed.getDay() == 0 || dataMed.getDay() == 6) {
      classe = 'item-med feriado';
    }

    switch (turnoMed) {
      case 'manha':
        $('#m' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome + " HRO" +
          '</span></div></div>');
        break;
      case 'tarde':
        $('#t' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="tardeItem ' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome + " HRO" +
          '</span></div></div>');
        break;
      case 'noite':
        $('#n' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="noiteItem ' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome + " HRO" +
          '</span></div></div>');
        break;
    }
  });
}

//Insere os medicos EDA em cada celula
addMedicoEda();

function addMedicoEda() {
  medsEda.forEach(med => {
    let dataMed = new Date(med.data);
    let dataReq = dataMed.getDate();
    let turnoMed = med.turno;
    let classe = 'item-med';

    if (dataMed.getDay() == 0 || dataMed.getDay() == 6) {
      classe = 'item-med feriado';
    }

    switch (turnoMed) {
      case 'manha':
        $('#m' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome + " EDA" +
          '</span></div></div>');
        break;
      case 'tarde':
        $('#t' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="tardeItem ' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome + " EDA" +
          '</span></div></div>');
        break;
      case 'noite':
        $('#n' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="noiteItem ' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome + " EDA" +
          '</span></div></div>');
        break;
    }
  });
}

//Insere os medicos sem função em cada celula
addMedicoSemFuncao();

function addMedicoSemFuncao() {
  medsEscalados.forEach(med => {
    let dataMed = new Date(med.data);
    let dataReq = dataMed.getDate();
    let turnoMed = med.turno;
    let classe = 'item-med';

    if (dataMed.getDay() == 0 || dataMed.getDay() == 6) {
      classe = 'item-med feriado';
    }

    switch (turnoMed) {
      case 'manha':
        $('#m' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome +
          '</span></div></div>');
        break;
      case 'tarde':
        $('#t' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="tardeItem ' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome +
          '</span></div></div>');
        break;
      case 'noite':
        $('#n' + dataMed.getDate()).find('.grid').append(
          '<div class="item"><div class="noiteItem ' + classe + '"><span value=' + med.id + '/' + turnoMed + '/' +
          dataReq + '>' + med.nome +
          '</span></div></div>');
        break;
    }
  });
}
//Adiciona os feriados na escala
addFeriados();

function addFeriados() {
  for (let i = 0; i < feriados.length; i++) {
    let dataFeriado = new Date(feriados[i].data);
    let descricao = feriados[i].descricao;

    /* DESCRIÇÃO DOS FERIADOS */
    $("#feriados").append("<small style='font-size: 1.2em;'>" + dataFeriado.getDate() + " - " + descricao + "</small><br>");

    $('#m' + dataFeriado.getDate()).find('.item-med').addClass("feriado");
    $('#t' + dataFeriado.getDate()).find('.item-med').addClass("feriado");
    $('#n' + dataFeriado.getDate()).find('.item-med').addClass("feriado");
  }
}