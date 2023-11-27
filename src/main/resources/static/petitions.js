
// when html is parsed,
document.addEventListener("DOMContentLoaded", function () {
    // Query API endpoint for all petitions
    fetch("/ciaraspetitions/petitions")
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error fetching petitions: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        // call function to display
        .then(data => displayPetitions(data))
        .catch(error => console.error("Error fetching all petitions:", error));
});
