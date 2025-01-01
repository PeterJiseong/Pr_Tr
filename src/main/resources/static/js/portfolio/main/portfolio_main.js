import paging from "../../paging.js";
function xmlTestBtn(){
    fetch('javascriptTest')
        .then((request)=>{
            console.log(request);
        })
}
const notionBlock = document.querySelector('#notionBlock');
window.addEventListener("DOMContentLoaded", ()=>{
    paging("/portfolio/notionMainPage","get",notionBlock);
})


