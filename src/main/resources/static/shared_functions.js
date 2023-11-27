// functions used to display search & browse results

function createPetitionCard(petition) {
    // get the container
    const card = document.createElement("div");
    card.classList.add("card", "shadow-sm");

    // html for each result
    card.innerHTML = `
            <div class="card-body">
                <p class="card-text">${petition.title}</p>
                <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                        <a href="/petition_detail.html?id=${petition.petition_id}" class="btn btn-sm btn-outline-secondary btn-view">View</a>
                    </div>
                </div>
            </div>
    `;
    return card;
}



function displayPetitions(allPetitions) {
    // find container
    const container = document.getElementById("ResultsContainer");
    // empty it
    container.innerHTML = "";

    // Are there petitions per the API call?
    if (allPetitions && allPetitions.length > 0) {
        // create a card for each
        allPetitions.forEach(petition => {
            const card = createPetitionCard(petition);
            container.appendChild(card);

            // find the button to view
            const viewButton = card.querySelector(".btn-view");
            // when it is clicked,
            viewButton.addEventListener("click", () => {
                // Get the petition ID
                const petitionId = petition.petition_id;

                // if view button is clicked, go to that detail page
                window.location.href = `/ciaraspetitions/petition_detail.html?id=${petitionId}`;
            });
        });
    } else {
        // if no petitions, say so
        const noResultsMessage = document.createElement("p");
        noResultsMessage.innerText = "No results found.";
        container.appendChild(noResultsMessage);
    }
}