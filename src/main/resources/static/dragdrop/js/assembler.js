    var tabelas = document.querySelectorAll('table');
    var diasSemana = ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'];

    // Preenche as datas no cabeçalho das tabelas
    DiasDatasSemanas(0);

    function DiasDatasSemanas(cont) {
      var header_sem = tabelas[cont].querySelectorAll('th');
      datas.forEach(element => {
        var data = new Date(element);

        var t = new Date("2019-01-01T10:00:00.000+00:00");
        console.log(t.getDate());

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

    //Retira os botões das células (td) que não são dias do mês
    limparCelulas();

    function limparCelulas() {
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
            let celsFilhosM = celsManha[num - 1].children;
            celsFilhosM[0].style.visibility = 'hidden';
            celsFilhosM[1].style.visibility = 'hidden';

            celsTarde[num - 1].className = '';
            let celsFilhosT = celsTarde[num - 1].children;
            celsFilhosT[0].style.visibility = 'hidden';
            celsFilhosT[1].style.visibility = 'hidden';

            celsNoite[num - 1].className = '';
            let celsFilhosN = celsNoite[num - 1].children;
            celsFilhosN[0].style.visibility = 'hidden';
            celsFilhosN[1].style.visibility = 'hidden';
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

    // Adiciona os metodos aos botoes
    addBotoes();

    function addBotoes() {
      let somadorGrid = 0;

      tabelas.forEach(element => {
        let celulas = element.querySelectorAll('td');
        addBtn(celulas, somadorGrid);
        somadorGrid += 21;
      });
    }
    //Funcao auxiliadora para adicionar metodos aos botoes
    function addBtn(cels, soma) {
      for (let index = 0; index < 21; index++) {
        let filhos = cels[index].children;
        let idCels = cels[index].getAttribute('id');
        filhos[0].setAttribute('onclick', 'removeItem(' + (index + soma) + ')');
        filhos[1].setAttribute('onclick', 'acionaMenu(event,' + (index + soma) + ',' + idCels + ')');
      }
    }

    //Insere os medicos M5 em cada celula
    addMedicoM5();

    function addMedicoM5() {
      medsM5.forEach(med => {
        let dataMed = new Date(med.data);
        let dataReq = dataMed.getDate();
        let turnoMed = med.turno;
        let classe = 'item-content';

        if (dataMed.getDay() == 0 || dataMed.getDay() == 6) {
          classe = 'item-content feriado';
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
        let classe = 'item-content';

        if (dataMed.getDay() == 0 || dataMed.getDay() == 6) {
          classe = 'item-content feriado';
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

    //Insere os medicos sem função em cada celula
    addMedicoSemFuncao();

    function addMedicoSemFuncao() {
      medsEscalados.forEach(med => {
        let dataMed = new Date(med.data);
        let dataReq = dataMed.getDate();
        let turnoMed = med.turno;
        let classe = 'item-content';

        if (dataMed.getDay() == 0 || dataMed.getDay() == 6) {
          classe = 'item-content feriado';
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

        $('#m' + dataFeriado.getDate()).find('.item-content').addClass("feriado");
        $('#t' + dataFeriado.getDate()).find('.item-content').addClass("feriado");
        $('#n' + dataFeriado.getDate()).find('.item-content').addClass("feriado");
      }
    }

    function chamarModal() {
      $('#modalbody').load(endereco + '/modal/relatoriosemanal');
      setTimeout(function () {
        $('#modalRelatorio').modal('show');
      }, 100);
    }