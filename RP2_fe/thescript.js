const loginDiv = document.getElementById("login_div");
const activeUserDiv = document.getElementById("active_user_div");
const submittedRequestsDiv = document.getElementById("submitted_requests_div");
const attnRequestsDiv = document.getElementById("attention_requests_div");
const newRequestDiv = document.getElementById("new_request_div");
const newAttachmentDiv = document.getElementById("new_attachment_div");
const focusDiv = document.getElementById("request_focus_div");

const reimbursementField = document.getElementById("proj_reimb_field");
const maxRMBField = document.getElementById("max_reimb_field");
const eventRMBValues = [0.8, 0.6, 0.75, 1, 0.9, 0.3];

var userInfo;
var userMaxRMB;



/////////////////////////////
//LogInDiv
//ActiveUserDiv
/////////////////////////////



async function logInUser() {
    const usernameIn = document.getElementById("login_username").value;
    const passIn = document.getElementById("login_password").value;

    //Clear imput fields
    document.getElementById('login_username').value = "";
    document.getElementById('login_password').value = "";

    const bodyData = {
        username: usernameIn,
        password: passIn
    };

    let url = 'http://localhost:7000/login';

    let userResponse = await fetch(url, {
        method: 'POST',
        body: JSON.stringify(bodyData)
    });

    let userResult = await userResponse.json();

    console.log(userResult);

    if (userResult == null) {
        document.getElementById("badLoginNote").innerHTML = "Invalid Log In Credentials";
        return;
    }
    //Not ideal - tokens? Cookies?
    userInfo = userResult;


    //Successful login
    loginDiv.style.display = "none";
    document.getElementById("badLoginNote").innerHTML = "";
    //Show new request button
    document.getElementById("new_request_button").style.visibility = "visible";

    activeUserDiv.style.display = "block";
    document.getElementById("welcome_header").innerHTML = `Welcome to the Tuition Reimbursement Management System, ${userResult.firstname} ${userResult.lastname}`;

    //Get requests and populate the relevant divs
    submittedRequestsDiv.style.display = "block";

    updateRequestDivs();
}

async function updateRequestDivs() {
    requestsUrl = `http://localhost:7000/requests/user/${userInfo.id}`;

    let requestResponse = await fetch(requestsUrl, {
        method: 'GET'
    });
    let requestsResult = await requestResponse.json();

    generateRequestsDiv(requestsResult[0]);
    generateAttentionRequestsDiv(requestsResult[1], 1);
    generateAttentionRequestsDiv(requestsResult[2], 2);
    generateAttentionRequestsDiv(requestsResult[3], 3);
    generateAttentionRequestsDiv(requestsResult[4], 4);

    //-Get all requests in the database that require the user's attention, such as approval
    attnRequestsDiv.style.display = "block";
}

function logOutUser() {
    //Remove all user-relevant data
    userInfo = null;
    userMaxRMB = null;

    //empty and hide the 'submitted_requests_div'
    clearRequestsArea();
    submittedRequestsDiv.style.display = "none";

    //empty and hide the 'attention_requests_div'
    attnRequestsDiv.style.display = "none";

    //empty and hide new request and new attachment divs
    clearNewRequestForm();
    newRequestDiv.style.display = "none";
    newAttachmentDiv.style.display = "none";

    //Clear all fields in 'active_user_div'
    document.getElementById("welcome_header").innerHTML = ``;

    //Change 'active_user_div' css to hide it
    document.getElementById("new_request_button").style.visibility = "hidden";
    activeUserDiv.style.display = "none";
    loginDiv.style.display = "block";

    //Clear the focus div
    clearFocusDiv();
    focusDiv.style.display="none";
}



/////////////////////////////
//submitted_requests_div
//attention_requests_div
/////////////////////////////



function generateRequestsDiv(requestList) {
    //Clear requests first
    clearRequestsArea();

    //Generate a div for each request
    requestList.forEach(request => {
        populateSingleRequest(request, submittedRequestsDiv);
    });
}

function generateAttentionRequestsDiv(requestList, userRoleNum){
    //userRoleNum: used to identify what role the logged in user has that is responsible for this request appearing here
    //1 - The user's own request.
    //2 - The user is the supervisor of the user who submitted the request
    //3 - The user is the department head of the user who submitted the request
    //4 - The user is a benefits coordinator

    //Generate a div for each request
    requestList.forEach(request => {
        populateSingleRequest(request, attnRequestsDiv, userRoleNum);
    });
}


function populateSingleRequest(requestData, destinationDiv, userRoleNum) {
    const divNum = destinationDiv.querySelectorAll(".single_request_div").length;
    
    let newDiv = document.createElement("div");
    newDiv.setAttribute("class", "single_request_div");

    let headerElem = document.createElement("p");

    let reqIdElem = document.createElement("p");
    reqIdElem.innerText = `Request Identification Number: ${requestData.id}`;

    let valueElem = document.createElement("p");
    valueElem.innerText = `Reimbursement Value: $${requestData.rmbValue}`;

    let infoButton = document.createElement("button");
    infoButton.setAttribute("onclick", `focusOnDiv(${requestData.id}, ${requestData.requestState}, ${requestData.eventId}, ${requestData.gradingId}, ${requestData.rmbValue})`);
    infoButton.innerText = `View Request Details`;

    headerElem.setAttribute("class", "single_request_element");
    reqIdElem.setAttribute("class", "single_request_element");
    valueElem.setAttribute("class", "single_request_element");
    infoButton.setAttribute("class", "single_request_element");

    newDiv.appendChild(headerElem);
    newDiv.appendChild(reqIdElem);
    newDiv.appendChild(valueElem);
    newDiv.appendChild(infoButton);

    
    let idString;
    if(userRoleNum) {
        //If this is an attention div, add in the appropriate functionality
        headerElem.innerText = `-This Request Requires Your Attention-`;
        idString = `single_request_attn_div_${divNum}`;
        infoButton.setAttribute("onclick", `focusOnDiv(${requestData.id}, ${requestData.requestState}, ${requestData.eventId}, ${requestData.gradingId}, ${requestData.rmbValue});
        generateAttnFocusButtons(${requestData.id}, ${requestData.requestState}, ${userRoleNum})`);
        newDiv.style.backgroundColor = "lightcoral";
    } else {
        headerElem.innerText = `-Your Submitted Request-`;
        idString = `single_request_div_${divNum}`;
        infoButton.setAttribute("onclick", `focusOnDiv(${requestData.id}, ${requestData.requestState}, ${requestData.eventId}, ${requestData.gradingId}, ${requestData.rmbValue})`);
        newDiv.style.backgroundColor = "#d0ecd0";
    }

    newDiv.setAttribute("id", `${idString}`);
    destinationDiv.appendChild(newDiv);
}


async function generateAttnFocusButtons(requestId, requestState, userRoleNum) {
    console.log("generating focus buttons");
    console.log(requestId);
    console.log(requestState);
    console.log(userRoleNum);

    let buttonFocusDiv = document.getElementById("focus_button_div");
    buttonFocusDiv.innerHTML = "";
    //Business functionality for moving the request throught the request states
    //Also Inform the user why they can see this particular request
    let reasonElem = document.createElement("p");
    buttonFocusDiv.appendChild(reasonElem);
    let buttonApproval = document.createElement("button");
    let optionElem = document.createElement("p");
    let moreInfoButtonOne = document.createElement("button");
    let moreInfoButtonTwo = document.createElement("button");
    let moreInfoButtonThree = document.createElement("button");


    reasonElem.setAttribute("class", "single_request_element");
    optionElem.setAttribute("class", "single_request_element");
    buttonApproval.setAttribute("class", "single_request_element");
    moreInfoButtonOne.setAttribute("class", "single_request_element");
    moreInfoButtonTwo.setAttribute("class", "single_request_element");
    moreInfoButtonThree.setAttribute("class", "single_request_element");


    //Another button for the BenCo to edit the request value!
    let benCoChangeRequestValue = document.createElement("button");

    let idString = 0;

    switch(requestState) {
        case 10: {
            if (userRoleNum == 2) {
                reasonElem.innerText = `As the Supervisor to the Requester, you must approve this request for it to move forward.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${20}, "${idString}"); generateAttnFocusButtons(${requestId}, ${20}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
                optionElem.innerText = `You may ask for additional information from the Requester before approval.`;
                moreInfoButtonOne.innerText = "Ask for information from Requester";
                moreInfoButtonOne.setAttribute("onclick", `updateRequestStatus(${requestId}, ${11}, "${idString}"); generateAttnFocusButtons(${requestId}, ${11}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(optionElem);
                buttonFocusDiv.appendChild(moreInfoButtonOne);
            }
        break;
        }

        case 11: {
            if(userRoleNum == 1) {
                reasonElem.innerText = `Your Supervisor needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${10})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 2) {
                reasonElem.innerText = `The information you asked the requester for has not yet been attached. You may approve the request regardless.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${20}, "${idString}"); generateAttnFocusButtons(${requestId}, ${20}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
            }
        } 
        break;

        case 20: {
            if(userRoleNum == 3) {
                reasonElem.innerText = `As the Department Head of the Requester, you must approve this request for it to move forward.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${30}, "${idString}"); generateAttnFocusButtons(${requestId}, ${30}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
                optionElem.innerText = `You may ask for additional information from the Requester or their Supervisor before approval.`;
                moreInfoButtonOne.innerText = "Ask for information from Requester";
                moreInfoButtonOne.setAttribute("onclick", `updateRequestStatus(${requestId}, ${21}, "${idString}"); generateAttnFocusButtons(${requestId}, ${21}, "${userRoleNum}")`);
                moreInfoButtonTwo.innerText = "Ask for information from Supervisor";
                moreInfoButtonTwo.setAttribute("onclick", `updateRequestStatus(${requestId}, ${22}, "${idString}"); generateAttnFocusButtons(${requestId}, ${22}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(optionElem);
                buttonFocusDiv.appendChild(moreInfoButtonOne);
                buttonFocusDiv.appendChild(moreInfoButtonTwo);
            }
        } 
        break;

        case 21: {
            if(userRoleNum == 1) {
                reasonElem.innerText = `Your Department Head needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${20})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 3) {
                reasonElem.innerText = `The information you asked the requester for has not yet been attached. You may approve the request regardless.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${30}, "${idString}"); generateAttnFocusButtons(${requestId}, ${30}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
                optionElem.innerText = `You may ask for additional information from the Supervisor of the Requester before approval.`;
                moreInfoButtonOne.innerText = "Ask for information from Supervisor";
                moreInfoButtonOne.setAttribute("onclick", `updateRequestStatus(${requestId}, ${23}, "${idString}"); generateAttnFocusButtons(${requestId}, ${23}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(optionElem);
                buttonFocusDiv.appendChild(moreInfoButtonOne);
            }
        } 
        break;

        case 22: {
            if(userRoleNum == 2) {
                reasonElem.innerText = `The Department Head of the Requester needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${20})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 3) {
                reasonElem.innerText = `The information you asked the Supervisor of the Requester for has not yet been attached. You may approve the request regardless.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${30}, "${idString}"); generateAttnFocusButtons(${requestId}, ${30}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
                optionElem.innerText = `You may ask for additional information from the Requester before approval.`;
                moreInfoButtonOne.innerText = "Ask for information from Requester";
                moreInfoButtonOne.setAttribute("onclick", `updateRequestStatus(${requestId}, ${23}, "${idString}"); generateAttnFocusButtons(${requestId}, ${23}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(optionElem);
                buttonFocusDiv.appendChild(moreInfoButtonOne);
            }
        } 
        break;

        case 23: {
            if(userRoleNum == 1) {
                reasonElem.innerText = `The Department Head needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${22})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 2) {
                reasonElem.innerText = `The Department Head of the Requester needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${21})`);
                buttonFocusDiv.appendChild(buttonApproval);
            }else if (userRoleNum == 3) {
                reasonElem.innerText = `None of the information you asked the Requester and their Supervisor for has not yet been attached. You may approve the request regardless.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${30}, "${idString}"); generateAttnFocusButtons(${requestId}, ${30}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
            }
        } 
        break;

        case 30: {
            if(userRoleNum == 4) {
                reasonElem.innerText = `As a benefits coordinator, you must approve this request for it to move forward.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${40}, "${idString}"); generateAttnFocusButtons(${requestId}, ${40}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
                optionElem.innerText = `You may ask for additional information from the Requester, their Supervisor, or their Department Head before approval.`;
                moreInfoButtonOne.innerText = "Ask for information from Requester";
                moreInfoButtonOne.setAttribute("onclick", `updateRequestStatus(${requestId}, ${31}, "${idString}"); generateAttnFocusButtons(${requestId}, ${31}, "${userRoleNum}")`);
                moreInfoButtonTwo.innerText = "Ask for information from Supervisor";
                moreInfoButtonTwo.setAttribute("onclick", `updateRequestStatus(${requestId}, ${32}, "${idString}"); generateAttnFocusButtons(${requestId}, ${32}, "${userRoleNum}")`);
                moreInfoButtonThree.innerText = "Ask for information from Department Head";
                moreInfoButtonThree.setAttribute("onclick", `updateRequestStatus(${requestId}, ${34}, "${idString}"); generateAttnFocusButtons(${requestId}, ${34}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(optionElem);
                buttonFocusDiv.appendChild(moreInfoButtonOne);
                buttonFocusDiv.appendChild(moreInfoButtonTwo);
                buttonFocusDiv.appendChild(moreInfoButtonThree);
            }
        } 
        break;

        case 31: {
            if(userRoleNum == 1) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${30})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 4) {
                reasonElem.innerText = `The information you asked the Requester for has not yet been attached. You may approve the request regardless.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${40}, "${idString}"); generateAttnFocusButtons(${requestId}, ${40}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
                optionElem.innerText = `You may ask for additional information from the Supervisor or Department Head of the Requester before approval.`;
                moreInfoButtonOne.innerText = "Ask for information from Supervisor";
                moreInfoButtonOne.setAttribute("onclick", `updateRequestStatus(${requestId}, ${33}, "${idString}"); generateAttnFocusButtons(${requestId}, ${33}, "${userRoleNum}")`);
                moreInfoButtonTwo.innerText = "Ask for information from Department Head";
                moreInfoButtonTwo.setAttribute("onclick", `updateRequestStatus(${requestId}, ${35}, "${idString}"); generateAttnFocusButtons(${requestId}, ${35}, "${userRoleNum}")v`);
                buttonFocusDiv.appendChild(optionElem);
                buttonFocusDiv.appendChild(moreInfoButtonOne);
                buttonFocusDiv.appendChild(moreInfoButtonTwo);
            }
        } 
        break;

        case 32: {
            if(userRoleNum == 2) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${30})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 4) {
                reasonElem.innerText = `The information you asked the Supervisor of the Requester for has not yet been attached. You may approve the request regardless.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${40}, "${idString}"); generateAttnFocusButtons(${requestId}, ${40}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
                optionElem.innerText = `You may ask for additional information from the Requester or their Department Head before approval.`;
                moreInfoButtonOne.innerText = "Ask for information from Requester";
                moreInfoButtonOne.setAttribute("onclick", `updateRequestStatus(${requestId}, ${33}, "${idString}"); generateAttnFocusButtons(${requestId}, ${33}, "${userRoleNum}")`);
                moreInfoButtonTwo.innerText = "Ask for information from Department Head";
                moreInfoButtonTwo.setAttribute("onclick", `updateRequestStatus(${requestId}, ${36}, "${idString}"); generateAttnFocusButtons(${requestId}, ${36}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(optionElem);
                buttonFocusDiv.appendChild(moreInfoButtonOne);
                buttonFocusDiv.appendChild(moreInfoButtonTwo);
            }
        } 
        break;

        case 33: {
            if(userRoleNum == 1) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${32})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 2) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${31})`);
                buttonFocusDiv.appendChild(buttonApproval);
            }else if (userRoleNum == 4) {
                reasonElem.innerText = `None of the information you asked the Requester and their Supervisor for has not yet been attached. You may approve the request regardless.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${40}, "${idString}"); generateAttnFocusButtons(${requestId}, ${40}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
                optionElem.innerText = `You may ask for additional information from the Department Head of the Requester before approval.`;
                moreInfoButtonOne.innerText = "Ask for information from Department Head";
                moreInfoButtonOne.setAttribute("onclick", `updateRequestStatus(${requestId}, ${37}, "${idString}"); generateAttnFocusButtons(${requestId}, ${37}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(optionElem);
                buttonFocusDiv.appendChild(moreInfoButtonOne);
            }
        } 
        break;

        case 34: {
            if(userRoleNum == 3) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${30})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 4) {
                reasonElem.innerText = `The information you asked the Department Head of the Requester for has not yet been attached. You may approve the request regardless.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${40}, "${idString}"); generateAttnFocusButtons(${requestId}, ${40}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
                optionElem.innerText = `You may ask for additional information from the Requester or their Supervisor before approval.`;
                moreInfoButtonOne.innerText = "Ask for information from Requester";
                moreInfoButtonOne.setAttribute("onclick", `updateRequestStatus(${requestId}, ${35}, "${idString}"); generateAttnFocusButtons(${requestId}, ${35}, "${userRoleNum}")`);
                moreInfoButtonTwo.innerText = "Ask for information from Supervisor";
                moreInfoButtonTwo.setAttribute("onclick", `updateRequestStatus(${requestId}, ${36}, "${idString}"); generateAttnFocusButtons(${requestId}, ${36}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(optionElem);
                buttonFocusDiv.appendChild(moreInfoButtonOne);
                buttonFocusDiv.appendChild(moreInfoButtonTwo);
            }
        } 
        break;

        case 35: {
            if(userRoleNum == 1) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${34})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 3) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${31})`);
                buttonFocusDiv.appendChild(buttonApproval);
            }else if (userRoleNum == 4) {
                reasonElem.innerText = `None of the information you asked the Requester and their Department Head for has not yet been attached. You may approve the request regardless.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${40}, "${idString}"); generateAttnFocusButtons(${requestId}, ${40}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
                optionElem.innerText = `You may ask for additional information from the Supervisor of the Requester before approval.`;
                moreInfoButtonOne.innerText = "Ask for information from Supervisor";
                moreInfoButtonOne.setAttribute("onclick", `updateRequestStatus(${requestId}, ${37}, "${idString}"); generateAttnFocusButtons(${requestId}, ${37}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(optionElem);
                buttonFocusDiv.appendChild(moreInfoButtonOne);

            }
        } 
        break;

        case 36: {
            if(userRoleNum == 2) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${34})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 3) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${32})`);
                buttonFocusDiv.appendChild(buttonApproval);
            }else if (userRoleNum == 4) {
                reasonElem.innerText = `None of the information you asked the Requester's Supervisor or Department Head for has not yet been attached. You may approve the request regardless.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${40}, "${idString}"); generateAttnFocusButtons(${requestId}, ${40}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
                optionElem.innerText = `You may ask for additional information from the Requester before approval.`;
                moreInfoButtonOne.innerText = "Ask for information from Requester";
                moreInfoButtonOne.setAttribute("onclick", `updateRequestStatus(${requestId}, ${37}, "${idString}"); generateAttnFocusButtons(${requestId}, ${37}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(optionElem);
                buttonFocusDiv.appendChild(moreInfoButtonOne);

            }
        } 
        break;

        case 37: {
            if(userRoleNum == 1) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${36})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 2) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${35})`);
                buttonFocusDiv.appendChild(buttonApproval);
            } else if (userRoleNum == 3) {
                reasonElem.innerText = `The Benefits Coordinator needs you to submit additional information prior to approving this request.`;
                buttonApproval.innerText = "Attach information here";
                buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${33})`);
                buttonFocusDiv.appendChild(buttonApproval);
            }else if (userRoleNum == 4) {
                reasonElem.innerText = `None of the information you asked the Requester, theiur Supervisor, or their Department Head for has not yet been attached. You may approve the request regardless.`;
                buttonApproval.innerText = "Approve request";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${40}, "${idString}"); generateAttnFocusButtons(${requestId}, ${40}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
            }
        } 
        break;

        case 40: {
            if(userRoleNum == 1) {

                //get the request info and grade type
                url = `http://localhost:7000/requests/${requestId}`;
                let requestResponse = await fetch(url, {
                    method: 'GET'
                });
                let requestData = await requestResponse.json();
                url = `http://localhost:7000/grade_types/${requestData.gradingId}`;
                let gradeTypeResponse = await fetch(url, {
                    method: 'GET'
                });
                let gradeTypeData = await gradeTypeResponse.json();

                if(gradeTypeData.passType == "No grade requirement") {
                    reasonElem.innerText = `You must upload an attachment confirming your presentation.`;
                    buttonApproval.innerText = "Attach information here";
                    buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${43})`);
                } else if (gradeTypeData.presentationRequired == false) {
                    reasonElem.innerText = `You must upload an attachment confirming your grade.`;
                    buttonApproval.innerText = "Attach information here";
                    buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${41})`);
                } else {
                    reasonElem.innerText = `You must upload an attachment confirming your grade and presentation.`;
                    buttonApproval.innerText = "Attach information here";
                    buttonApproval.setAttribute("onclick", `displayNewAttachmentForm(${requestId}, ${42})`);
                }
                buttonFocusDiv.appendChild(buttonApproval);

            }
        } 
        break;

        case 41: {
            if(userRoleNum == 4) {
                reasonElem.innerText = `As a benefits coordinator, you must confirm that a passing grade was attached to this request to grant the reimbursement.`;
                buttonApproval.innerText = "Confirm passing grade";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${50}, "${idString}"); generateAttnFocusButtons(${requestId}, ${50}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
            }
        } 
        break;

        case 42: {
            if(userRoleNum == 4) {
                reasonElem.innerText = `As a benefits coordinator, you must confirm that a passing grade was attached to this request.`;
                buttonApproval.innerText = "Confirm passing grade";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${43}, "${idString}"); generateAttnFocusButtons(${requestId}, ${43}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
            }
        } 
        break;

        case 43: {
            if(userRoleNum == 2) {
                reasonElem.innerText = `As the supervisor of the requester, you must confirm that a satisfactory presentation was attached to this request to grant the reimbursement.`;
                buttonApproval.innerText = "Confirm satisfactory presentation";
                buttonApproval.setAttribute("onclick", `updateRequestStatus(${requestId}, ${50}, "${idString}"); generateAttnFocusButtons(${requestId}, ${50}, "${userRoleNum}")`);
                buttonFocusDiv.appendChild(buttonApproval);
            }
        } 
        break;

      }
}

function clearRequestsArea() {
    submittedRequestsDiv.innerHTML = "";
    attnRequestsDiv.innerHTML = "";
}




/////////////////////////////////
//new_request_div
/////////////////////////////////



async function displayNewRequestForm() {
    //Hide other elements
    submittedRequestsDiv.style.display = "none";
    attnRequestsDiv.style.display = "none";

    //Find out how much reimbursement the user can still recieve
    url = `http://localhost:7000/requests/user/rmb/${userInfo.id}`;

    let rmbResponse = await fetch(url, {
        method: 'GET'
    });

    let rmbResult = await rmbResponse.json();

    maxRMBField.innerHTML = `$ ${rmbResult}`;
    userMaxRMB = rmbResult;
    
    //Get the grading formats and populate the html
    //Verify all data is valid
    //-If not, stop and display what is wrong with it
    url = 'http://localhost:7000/grade_types';

    let gradeTypesResponse = await fetch(url, {
        method: 'GET'
    });

    let gradeTypesResult = await gradeTypesResponse.json();

    document.getElementById("grade_radio_div").innerHTML = "";
    gradeTypesResult.forEach(addGradeRadio);


    //Clear all fields in 'new_request_div'
    clearNewRequestForm();
    //Alter the css of 'new_request_div' to unhide it
    newRequestDiv.style.display = "block";

    //Hide new request button
    document.getElementById("new_request_button").style.visibility = "hidden";

}

function editProjectedRMB() {
    let eventType = -1;
    const eventRadios = document.getElementsByName("event_type_radio");
    eventRadios.forEach(radio => {
        if(radio.checked) {
            eventType = radio.value;
        }
    });
    if(eventType != -1 && document.getElementById("request_cost").value >= 0) {
        let displayedValue = eventRMBValues[eventType-1] * document.getElementById("request_cost").value;
        if(displayedValue > userMaxRMB) {
            displayedValue = userMaxRMB;
        }
        reimbursementField.innerHTML = `$ ${displayedValue}`;
    }
}

function addGradeRadio(gradeFormat) {
    const divNum = document.getElementById("grade_radio_div").querySelectorAll(".grade_format_radio").length +1;
    let radioElement = document.createElement("input");
    radioElement.setAttribute("type", "radio");
    radioElement.setAttribute("class", "grade_format_radio");
    radioElement.setAttribute("name", `grade_format_radio`);
    radioElement.setAttribute("id", `grade_radio_${divNum}`);
    radioElement.setAttribute("value", `${divNum}`);
    let labelElement = document.createElement("label");
    labelElement.setAttribute("for", `grade_radio_${divNum}`);
    labelElement.innerHTML = `${gradeFormat.passType}; Passing grade: ${gradeFormat.passingGrade}; Presentation Required: ${gradeFormat.presentationRequired}`;
    let breakElement = document.createElement("br");
    document.getElementById("grade_radio_div").appendChild(radioElement);
    document.getElementById("grade_radio_div").appendChild(labelElement);
    document.getElementById("grade_radio_div").appendChild(breakElement);
}


async function submitNewRequest() {
    //Pull all data out of the form

    let eventGrad = -1;
    const gradeFormatRadios = document.getElementsByName("grade_format_radio");
    gradeFormatRadios.forEach(radio => {
        if(radio.checked) {
            eventGrad = radio.value;
        }
    });

    let eventType = -1;
    const eventRadios = document.getElementsByName("event_type_radio");
    eventRadios.forEach(radio => {
        if(radio.checked) {
            eventType = radio.value;
        }
    });

    const eventDate = document.getElementById("request_date").value;
    const eventTime = document.getElementById("request_time").value;
    const eventLoca = document.getElementById("request_location").value;
    let eventCost = document.getElementById("request_cost").value;
    const eventDesc = document.getElementById("request_description").value;
    const eventJust = document.getElementById("request_justification").value;


    let requestRMB;
    if(eventType != -1 && document.getElementById("request_cost").value >= 0) {
        let displayedValue = eventRMBValues[eventType-1] * document.getElementById("request_cost").value;
        if(displayedValue > userMaxRMB) {
            displayedValue = userMaxRMB;
        }
        requestRMB = displayedValue;
    }

    if(eventType == -1 || eventGrad == -1 || eventCost < 0) {
        badSubmitNote.innerHTML = "Invalid request - you must specify a cost, a grading format, and en event type.";
        return;
    } else if (eventCost > 10000) {
        eventCost = 100000; 
    }

    //Get the document data, if possible
    const dateReadout = new Date(eventDate + " " + eventTime);
    const dateValue = dateReadout.getTime();

    //Convert the data to JSON
    reqData = {
        id: userInfo.id,
        eventTime: dateValue,
        eventLoca: eventLoca,
        eventCost: eventCost,
        eventDesc: eventDesc,
        eventType: eventType,
        eventGrad: eventGrad,
        eventJust: eventJust,
        rmbValue: requestRMB
    };


    //Send it to the appropriate route to make a new request/event
    url = 'http://localhost:7000/requests';

    let requestResponse = await fetch(url, {
        method: 'POST',
        body: JSON.stringify(reqData)
    });

    let requestResult = await requestResponse.json();

    //Wait for a response
    //If not successful, display a relevant error message
    //If successful, call 'populateSingleRequest' with the new request to generate the relevant html for it
    if(requestResult == null) {
        console.log("New request failed!")
    } else {
        populateSingleRequest(requestResult, submittedRequestsDiv);
    }
    


    

    //Clear all fields in 'new_request_div'
    clearNewRequestForm();
    badSubmitNote.innerHTML = "";

    //Reload all requests
    updateRequestDivs()

    //Alter the css of 'new_request_div' to unhide it
    newRequestDiv.style.display = "none";

    //Display other divs
    submittedRequestsDiv.style.display = "block";
    attnRequestsDiv.style.display = "block";

    //Show new request button
    document.getElementById("new_request_button").style.visibility = "visible";

}

function cancelRequest() {
    //Clear all fields in 'new_request_div'
    clearNewRequestForm();

    //Alter the css of 'new_request_div' to unhide it
    newRequestDiv.style.display = "none";

    //Display other divs
    submittedRequestsDiv.style.display = "block";
    attnRequestsDiv.style.display = "block";

    //Show new request button
    document.getElementById("new_request_button").style.visibility = "visible";
}

function clearNewRequestForm() {
    console.log("Clearing form");
    document.getElementById("request_date").value = "";
    document.getElementById("request_time").value = "";
    document.getElementById("request_location").value = "";
    document.getElementById("request_cost").value = "";
    document.getElementById("request_description").value = "";
    document.getElementById("request_justification").value = "";

    const gradeFormatRadios = document.getElementsByName("grade_format_radio");
    gradeFormatRadios.forEach(radio => {
        radio.checked = false;
    });

    const eventRadios = document.getElementsByName("event_type_radio");
    eventRadios.forEach(radio => {
        radio.checked = false;
    });
    document.getElementById("proj_reimb_field").innerHTML = "-";
}

//Creates a new 'single_upload_div' to allow the user to upload a new file and description
function addNewDocUploadDiv() {
    const fileUploadArea = document.getElementById("file_upload_area");

    const divNum = fileUploadArea.querySelectorAll(".single_upload_div").length + 1;

    let newDivButton = document.getElementById("new_upload_div_button");

    let newDiv = document.createElement("div");
    newDiv.className = "single_upload_div";

    let labelOne = document.createElement("label");
    labelOne.setAttribute("for", `file_input_${divNum}`);
    labelOne.innerText = "Upload relevant file:";

    let fileInput = document.createElement("input");
    fileInput.setAttribute("type", "file");
    fileInput.setAttribute("id", `file_input_${divNum}`);
    fileInput.setAttribute("accept", ".pdf, .png, .jpeg, .txt, .doc");

    let labelTwo = document.createElement("label");
    labelTwo.setAttribute("for", `file_descript_${divNum}`);
    labelTwo.innerText = "File Description / Additional Information:";

    let textArea = document.createElement("textarea");
    textArea.setAttribute("type", "file");
    textArea.setAttribute("id", `file_descript_${divNum}`);
    textArea.setAttribute("rows","5");
    textArea.setAttribute("cols","70");

    fileUploadArea.append(newDiv);
    fileUploadArea.insertBefore(newDivButton, newDiv.nextSibling);
    newDiv.append(document.createElement("br"));
    newDiv.append(labelOne);
    newDiv.append(fileInput);
    newDiv.append(document.createElement("br"));
    newDiv.append(labelTwo);
    newDiv.append(textArea);

}



/////////////////////////////////////
//new_attachment_div
/////////////////////////////////////



function displayNewAttachmentForm(requestId, newRequestState) {
    newAttachmentDiv.style.display = "block";
    clearNewAttachmentForm();
    //Set the button onclick to send a requets to the backend to:
    //Create the new attachment + Update the request status code
    //The button needs to pass: 'requestId, requestState, ' as variables
    document.getElementById("attachment_submit_button").setAttribute("onclick", `submitNewAttachment(${requestId}, ${newRequestState})`)
}

function clearNewAttachmentForm() {
    document.getElementById("file_add_input_1").value = "";
    document.getElementById("file_add_descript_1").value = "";
    document.getElementById("attachment_submit_button").setAttribute("onclick", "");
}

async function submitNewAttachment(requestId, requestState) {
    let filepath = document.getElementById("file_add_input_1").value;
    const text = document.getElementById("file_add_descript_1").value;

    filepath = "Placeholder";
    
    let bodyData = {
        requestId: requestId,
        requestState: requestState,
        filepath: filepath,
        text: text
    }

    url = 'http://localhost:7000/requests/add_attachment';

    let attachmentResponse = await fetch(url, {
        method: 'PATCH',
        body: JSON.stringify(bodyData)
    });

    let attachmentData = await attachmentResponse.json();

    console.log(attachmentData);
    newAttachmentDiv.style.display = "none";
    clearNewAttachmentForm();
    //Update the requests
    updateRequestDivs();
    clearFocusDiv();
}


//Updates the status code of a request without an attachment. Used for requesting attachments and moving up the business logic.
async function updateRequestStatus(requestId, newStatus, requestDivId) {
    const bodyData = {
        requestId: requestId,
        requestState: newStatus
    };

    url = 'http://localhost:7000/requests';

    let requestResponse = await fetch(url, {
        method: 'PATCH',
        body: JSON.stringify(bodyData)
    });

    let requestData = await requestResponse.json();

    updateRequestDivs();
    clearFocusDiv();

}



/////////////////////////////////
//request_focus_div
/////////////////////////////////



//Populate the 'request_focus_div' with info on the relevant request
async function focusOnDiv(requestId, requestState, requestEventId, requestGradingId, requestRmbValue, attnInfo) {
    console.log("Focusing");
    focusDiv.style.display = "block";

    clearFocusDiv();
    //Get all of the relevant info on the request
    //Request State info
    url = `http://localhost:7000/request_states/${requestState}`;

    let requestStateResponse = await fetch(url, {
        method: 'GET'
    });

    let requestStateData = await requestStateResponse.json();

    //Get the event info ('events' and 'event_categories' tables)

    //Request.event_id -> eventcontroller get eventbyid
    url = `http://localhost:7000/events/${requestEventId}`;

    let eventResponse = await fetch(url, {
        method: 'GET'
    });

    let eventData = await eventResponse.json();

    //Event.eventType -> eventypecontroller getEventCategoryById
    url = `http://localhost:7000/event_categories/${eventData.eventTypeId}`;

    let eventCategoryResponse = await fetch(url, {
        method: 'GET'
    });

    let eventCategoryData = await eventCategoryResponse.json();

    //Get grading type and pass requirements ('grade_types' table)
    url = `http://localhost:7000/grade_types/${requestGradingId}`;

    let gradeTypeResponse = await fetch(url, {
        method: 'GET'
    });
    let gradeTypeData = await gradeTypeResponse.json();
    
    //Get all attachments for the request ('attachments' table)
    url = `http://localhost:7000/attachments/requests/${requestId}`;

    let attachmentResponse = await fetch(url, {
        method: 'GET'
    });
    let attachmentData = await attachmentResponse.json();

    //Generate HTML for all of these
    let focusHeaderElem = document.createElement("h3");
    focusHeaderElem.innerHTML = "Request Details";
    focusHeaderElem.style.textAlign = "center";

    let requestIdElem = document.createElement("p");
    requestIdElem.innerText = `Request Identification Number: ${requestId}`;

    let requestStateElem = document.createElement("p");
    requestStateElem.innerText = `Request State: ${requestStateData.info}`;

    let valueElem = document.createElement("p");
    valueElem.innerText = `Reimbursement Value: $${requestRmbValue}`;

    let gradeTypeElem = document.createElement("p");
    gradeTypeElem.innerText = `${gradeTypeData.passType}; Passing grade: ${gradeTypeData.passingGrade}; Presentation Required: ${gradeTypeData.presentationRequired}`;
 
    let eventCategoryElem = document.createElement("p");
    eventCategoryElem.innerText = `Event type: ${eventCategoryData.category}`;

    let eventDescElem = document.createElement("p");
    eventDescElem.innerText = `Event description: ${eventData.description}`;
    let eventJustElem = document.createElement("p");
    eventJustElem.innerText = `Event justification: ${eventData.justification}`;
    let eventLocElem = document.createElement("p");
    eventLocElem.innerText = `Event location: ${eventData.eventLocation}`;

    const recallDate = new Date(eventData.eventTime);

    let eventTimeElem = document.createElement("p");
    eventTimeElem.innerText = `Event time: ${recallDate}`;

    focusDiv.append(focusHeaderElem);
    focusDiv.append(requestIdElem);
    focusDiv.append(requestStateElem);
    focusDiv.append(valueElem);
    focusDiv.append(gradeTypeElem);
    focusDiv.append(eventCategoryElem);
    focusDiv.append(eventLocElem);
    focusDiv.append(eventTimeElem);
    focusDiv.append(eventDescElem);
    focusDiv.append(eventJustElem);

    let attachmentAreaHeader = document.createElement("h4");
    attachmentAreaHeader.innerHTML = "Attachments:";
    focusDiv.append(attachmentAreaHeader);

    attachmentData.forEach(attachment => {
        createSingleAttachmentFocusDiv(attachment, focusDiv);
    });
    
    focusDiv.scrollIntoView();


}

function createSingleAttachmentFocusDiv(attachment, focusDiv) {
    let newAttachmentDiv = document.createElement("div");
    let attachmentFilePath = document.createElement("p");
    attachmentFilePath.innerHTML = attachment.filepath;
    let attachnmentText = document.createElement("p");
    attachnmentText.innerHTML = attachment.addedText;
    focusDiv.append(newAttachmentDiv);
    focusDiv.append(attachmentFilePath);
    focusDiv.append(attachnmentText);
}



function hideFocusDiv() {
    focusDiv.style.display = "none";
}

function clearFocusDiv() {
    console.log("Clearing focus div");
    focusDiv.innerHTML = "";
    let newDiv = document.createElement("div");
    newDiv.setAttribute("id", "focus_button_div");
    focusDiv.append(newDiv);
}

