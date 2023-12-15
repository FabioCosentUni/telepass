$('#autostradaSelect').on('change', function() {
    // Nascondi tutti i select di entrata e uscita
    $('#entrataSelect, #uscitaSelect').hide();


    // Ottieni il valore selezionato nel primo select
    var selectedAutostrada = $(this).val();

    // Carica dinamicamente le opzioni nel secondo select in base alla selezione nel primo select
    if (selectedAutostrada === 'uno') {
        // Opzioni per l'autostrada 1
        $('#entrataSelect').html('<option value="1">A1 1</option><option value="2">A11 2</option>');
        $('#uscitaSelect').html('<option value="1">A1 1</option><option value="2">A11 2</option>');
    } else if (selectedAutostrada === '2') {
        // Opzioni per l'autostrada 2
        $('#entrataSelect').html('<option value="3">Entrata 3</option><option value="4">Entrata 4</option>');
        $('#uscitaSelect').html('<option value="3">Uscita 3</option><option value="4">Uscita 4</option>');
    }

    // Mostra il secondo select appropriato
    $('#entrataSelect, #uscitaSelect').show();
});