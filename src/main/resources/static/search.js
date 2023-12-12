
// when html is parsed,
document.addEventListener("DOMContentLoaded", function () {
    const searchForm = document.getElementById("searchForm");

    // when the search button is clicked
    searchForm.addEventListener("submit", function (event) {
        event.preventDefault();

        //extract keyword
        const searchKeyword = document.getElementById("searchKeyword").value;

        // Query API endpoint for searching title & description
        fetch(`/ciaraspetitions/petitions/search?keyword=${encodeURIComponent(searchKeyword)}`)
            .then(response => response.json())
            .then(data => {
                // call function to display
                displayPetitions(data);
            })
            .catch(error => console.error("Error fetching search results:", error));
    });
});
