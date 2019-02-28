var grupoSalmao = [{ 'nome': 'Beth', 'crm': 2309 }, { 'nome': 'Lyvia', 'crm': 3311 }, { 'nome': 'Cicero', 'crm': 7872 }, { 'nome': 'Gilmar', 'crm': 3644 }];
var grupoVerde = [{ 'nome': 'Carlos', 'crm': 1298 }, { 'nome': 'Caio', 'crm': 3498 }];
var grupoAzul = [{ 'nome': 'Marcelo', 'crm': 2399 }, { 'nome': 'Igor', 'crm': 2298 }];
var grupoCoral = [{ 'nome': 'Plinio', 'crm': 3211 }, { 'nome': 'Jorge', 'crm': 5466 }];
var grupoChocolate = [{ 'nome': 'Cyro', 'crm': 4432 }, { 'nome': 'J Vitor', 'crm': 8832 }];

montadorTabela(grupoSalmao, 'salmao');
montadorTabela(grupoVerde, 'verde');
montadorTabela(grupoAzul, 'azul');
montadorTabela(grupoCoral, 'coral');
montadorTabela(grupoChocolate, 'chocolate');

var somatorio;
var crm;
function montadorTabela(grupo, cor) {
    grupo.forEach(med => {
        somatorio = 0;
        crm = med.crm;

        $('tbody').append('<tr id="' + med.crm + '" class="' + cor + '"></tr>');
        $('#' + med.crm).append('<th>' + med.nome + '</th>');

        addTurnosFolgaPorSemana(med, semana_1, 0);
        addTurnosFolgaPorSemana(med, semana_2, 1);
        addTurnosFolgaPorSemana(med, semana_3, 2);
        addTurnosFolgaPorSemana(med, semana_4, 3);
        addTurnosFolgaPorSemana(med, semana_5, 4);
        addTurnosFolgaPorSemana(med, semana_6, 5);

        $('#' + crm).append('<td class="text-center">' + somatorio + '</td>');
    });
}

function addTurnosFolgaPorSemana(med, semana, index) {
    try {
        let verificador = false;
        semana.forEach(sem => {
            if (med.crm == sem.crm) {
                $('#' + sem.crm).append('<td class="text-center">' + sem.cont + '</td>');
                somatorio += sem.cont;
                verificador = true;
            }
        });
        //Se o medico não tirou nenhum serviço na semana, então ele tem todos os turnos de folga
        if (!verificador) {
            try {
                $('#' + crm).append('<td class="text-center">' + turnosTotaisFolga[index] + '</td>');
                somatorio += turnosTotaisFolga[index];
            } catch (e) {
                console.error("TurnosTotaisFolga não tem todos os indexs: " + e.message);
            }
        }
    } catch (e) {
        console.error("Não tem todas as semanas: " + e.message);
    }
}