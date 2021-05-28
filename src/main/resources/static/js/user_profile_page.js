const userId = 1;

const setClassByAttribute = (className, innerHtmlValue) => {
    let listOfClassName = document.getElementsByClassName(className);
    for (let i = 0; i < listOfClassName.length; ++i) {
        listOfClassName[i].innerHTML = innerHtmlValue;
    }
};


const getUserRegister = () => {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/register/" + userId);

    xhr.onload = () => {
        let jsonObj = JSON.parse(xhr.responseText);
        let emailId = jsonObj.emailId;
        let userName = jsonObj.userName;
        let isAdmin = jsonObj.isAdmin ? "Admin" : "User";
        setClassByAttribute('userid', userId);
        setClassByAttribute('firstname', userName);
        setClassByAttribute('email', emailId);
        setClassByAttribute('usertype', isAdmin);
    }

    xhr.send();
}

const getUserProfile = () => {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/user/sociallinks/" + userId);

    xhr.onload = () => {
        let jsonObj = JSON.parse(xhr.responseText);

        let facebookUrl = jsonObj[0];
        let instagramUrl = jsonObj[1];
        let twitterUrl = jsonObj[2];

        setClassByAttribute('facebook', facebookUrl);
        setClassByAttribute('twitter', twitterUrl);
        setClassByAttribute('instagram', instagramUrl);
    }

    xhr.send();
}

const getUserGallery = () => {
    let imageUrl = "";  // default url
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/api/user/gallery/" + userId);

    xhr.onload = () => {
        let jsonObj = JSON.parse(xhr.responseText);

        imageUrl = jsonObj[0];

        let imageUrlList = document.getElementsByClassName('imageurl');

        for (let i = 0; i < imageUrlList.length; i++) {
            imageUrlList[i].src = imageUrl
        }
    }

    xhr.send();
}


document.addEventListener("DOMContentLoaded", getUserRegister);
document.addEventListener("DOMContentLoaded", getUserGallery);
document.addEventListener("DOMContentLoaded", getUserProfile);
