// MENU PARA ADICIONAR MEDICOS

//Quando o mouse fica fora da div menu
$('.menu-med-add').mouseleave(function () {
    var menuAcionado = $(this);
    // $('table').click(function(){
    menuAcionado.hide();
    $('#func-med').hide();
    $('#list-med-ass').hide();
    $('#list-med-cont').hide();
    $('.add-medico-cont').css('background', '#fff');
    $('.add-medico-ass').css('background', '#fff');
    // });
});

//ACIONADOR DO MENU
function acionaMenu(event, index, id) {
    let req = '';

    if (id != null) {
        let idDiv = id.getAttribute('id');
        let dia;
        let turno;
        if (idDiv == 2) {
            turno = id.getAttribute('id').charAt(0);
            dia = id.getAttribute('id').charAt(1);
        } else {
            turno = id.getAttribute('id').charAt(0);
            dia = id.getAttribute('id').slice(1);
        }
        diaDoClick = dia;

        //turnoDoClick é uma váriavel global; recebe o turno corrente
        switch (turno) {
            case 'm':
                req = 'manha/' + dia;
                turnoDoClick = 'manha';
                break;
            case 't':
                req = 'tarde/' + dia;
                turnoDoClick = 'tarde';
                break;
            case 'n':
                req = 'noite/' + dia;
                turnoDoClick = 'noite';
                break;
        }
    }

    //AJAX
    $('.menu-med-add').load(endereco + '/escaladragdrop/listamedicos/' + req);
    console.log(endereco + '/escaladragdrop/listamedicos/' + req);

    //Após o tempo de 100 ms executa o código abaixo
    setTimeout(function () {
        //Posiciona o menu de acordo com o botão acionado e o tamanho da tela
        var tamTela = screen.width;
        var coordX = event.pageX;
        if (coordX < (tamTela / 2)) {
            $('.div-medico').css('float', 'left');
            $('.div-funcao').css('float', 'left');
            $('.menu-med-add').css('top', event.pageY - 20);
            $('.menu-med-add').css('left', coordX - 10);
            $('.menu-med-add').show();

            $('#list-med-ass').hide();
        } else {
            $('.div-medico').css('float', 'right');
            $('.div-funcao').css('float', 'right');
            $('.menu-med-add').css('top', event.pageY - 20);
            $('.menu-med-add').css('left', coordX - 650);
            $('.menu-med-add').show();
        }


        // $('#list-med-ass').show();


        //Mouse em cima do medico associado
        $('#med-ass').mouseenter(function () {
            $('.add-medico').css('background', '#fff');
            $('#list-med-cont').hide();
            $('#list-med-ass').show();
            $('.div-funcao:visible').hide();
        });
        // //Mouse em cima do medico contratado
        // $('#med-cont').mouseenter(function () {
        //     $('.add-medico').css('background', '#fff');
        //     $('#list-med-ass').hide();
        //     $('#list-med-cont').show();
        //     $('.div-funcao:visible').hide();
        // });
        // //Mostra o div funcao do respectivo medico
        // $('.med').click(function () {
        //     $('.div-funcao:visible').hide();
        //     let idFunc = $(this).attr('id') + "-func";
        //     $('#' + idFunc).show();
        // });
        // //Altera a cor quando em cima da lista de medicos
        // $('.add-medico').mouseenter(function () {
        //     $('.add-medico').css('background', '#fff');
        //     $(this).css('background', '#e0e0e0');
        // });
        // //Altera a cor quando em cima do medico associado
        // $('.add-medico-ass').mouseenter(function () {
        //     $('.add-medico-cont').css('background', '#fff');
        //     $(this).css('background', '#e0e0e0');
        // });
        // //Altera a cor quando em cima do medico contratado
        // $('.add-medico-cont').mouseenter(function () {
        //     $('.add-medico-ass').css('background', '#fff');
        //     $(this).css('background', '#e0e0e0');
        // });
        // //Altena os checkbox
        // $('[type=checkbox]').change(function () {
        //     let nome = $(this).attr('name');
        //     if (nome == 'm5') {
        //         $('[name=hro]').prop('checked', false);
        //     } else {
        //         $('[name=m5]').prop('checked', false);
        //     }
        // });
        //gridIndex é uma variável global
        //Recebe o index do grid atual
        gridIndex = index;

    }, 500);
}