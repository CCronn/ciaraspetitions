
// wait for html to parse,
document.addEventListener("DOMContentLoaded", function () {
    // pull petition_id from url
    const petitionId = getPetitionIdFromUrl();

    // if id is available
    if (petitionId !== null) {
        //query api to pull info for that petition
        fetch(`/petitions/${petitionId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error fetching petition details: ${response.status} ${response.statusText}`);
                }
                return response.json();
            })
            .then(data => {
                //console.log("API Response:", data); // Log the API response
                displayPetitionDetails(data);
            })
            .catch(error => console.error("Error fetching petition details:", error));
    } else {
        console.error("Petition ID is undefined or null.");
    }
});


function displayPetitionDetails(petition) {
    // find elements and fill with values
    document.getElementById("petitionTitle").innerText = petition.title || "Title Not Available";
    document.getElementById("petitionCreator").innerText = petition.forename || "Forename Not Available";
    document.getElementById("petitionDescription").innerText = petition.description || "Description Not Available";
    document.getElementById("petitionId").innerText = petition.petition_id || "Id Not Available";

    // get the container to hold signers
    const signersContainer = document.getElementById("signersContainer");
    signersContainer.innerHTML = "";

    if (petition.signers && petition.signers.length > 0) {
        const signersList = document.createElement("ul");

        petition.signers.forEach(signer => {
            // create a list item for each signer
            const signerItem = document.createElement("li");
            signerItem.innerText = `${signer.forename} ${signer.surname}`;
            signersList.appendChild(signerItem);
        });

        signersContainer.appendChild(signersList);
    } else { // else set it to no signers
        const noSignersMessage = document.createElement("p");
        noSignersMessage.innerText = "No signers for this petition.";
        signersContainer.appendChild(noSignersMessage);
    }
}

function getPetitionIdFromUrl() {
    // Get URL params
    const url = new URLSearchParams(window.location.search);
    const id = url.get("id");
    //console.log("Extracted petition ID from URL:", id);

    // Return id or null
    return id || null;
}



// wait for html to parse,
document.addEventListener("DOMContentLoaded", function () {
    //get id
    const petitionId = getPetitionIdFromUrl();
    //find sign form at bottom of detail page
    const signForm = document.getElementById("signForm");

    // set the action to the api endpoint for signing
    if (signForm) {
        signForm.action = `/petitions/${petitionId}/sign`;
    } else {
        console.error("Sign form not found.");
    }
});