function validateForm() {
    var startDateInput = document.getElementById('startDate');
    var endDateInput = document.getElementById('endDate');
    var messageContainer = document.getElementById('validationMessage');

    var startDate = new Date(startDateInput.value);
    var endDate = new Date(endDateInput.value);

    if (startDate > endDate) {
        messageContainer.innerHTML = 'Data początkowa nie może być późniejsza niż data końcowa.';
        return false;
    }

    messageContainer.innerHTML = '';

    return true;
}

