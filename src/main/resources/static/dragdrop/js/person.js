// MENU PARA ADICIONAR MEDICOS

//Quando o mouse fica fora da div menu
$(".menu-med-add").mouseleave(function () {
    var menuAcionado = $(this);
    // $("table").click(function(){
    menuAcionado.hide();
    $("#func-med").hide();
    $("#list-med-ass").hide();
    $("#list-med-cont").hide();
    $(".add-medico-cont").css("background", "#fff");
    $(".add-medico-ass").css("background", "#fff");
    // });
});

//ACIONADOR DO MENU
function acionaMenu(event, index, id) {
    let req = "";

    if (id != null) {
        let idDiv = id.getAttribute("id");
        let dia;
        let turno;
        if (idDiv == 2) {
            turno = id.getAttribute("id").charAt(0);
            dia = id.getAttribute("id").charAt(1);
        } else {
            turno = id.getAttribute("id").charAt(0);
            dia = id.getAttribute("id").slice(1);
        }
        diaDoClick = dia;

        //turnoDoClick é uma váriavel global; recebe o turno corrente
        switch (turno) {
            case "m":
                req = "manha/" + dia;
                turnoDoClick = "manha";
                break;
            case "t":
                req = "tarde/" + dia;
                turnoDoClick = "tarde";
                break;
            case "n":
                req = "noite/" + dia;
                turnoDoClick = "noite";
                break;
        }
    }

    //AJAX
    //Carrega o menu na página
    $(".menu-med-add").load(endereco + "/escaladragdrop/listamedicos/" + req);

    setTimeout(function () {
        //Posiciona o menu de acordo com o botão acionado e o tamanho da tela
        var tamTela = screen.width;
        var coordX = event.pageX;
        if (coordX < (tamTela / 2)) {
            $(".div-medico").css("float", "left");
            $(".div-funcao").css("float", "left");
            $(".menu-med-add").css("top", event.pageY - 20);
            $(".menu-med-add").css("left", coordX - 10);
            $(".menu-med-add").show();
        } else {
            $(".div-medico").css("float", "right");
            $(".div-funcao").css("float", "right");
            $(".menu-med-add").css("top", event.pageY - 20);
            $(".menu-med-add").css("left", coordX - 650);
            $(".menu-med-add").show();
        }
    }, 200);

    //gridIndex é uma variável global
    //Recebe o index do grid atual
    gridIndex = index;
}