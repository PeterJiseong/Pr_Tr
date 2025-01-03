import paging from "../paging.js";
const submitUrl = "/guest/guestBoardWrite";
const submitBtn = document.querySelector(".submit-btn");
const replaceArea = document.querySelector(".guestBoard-list")
const formName = document.querySelector("#name");
const formTextarea = document.querySelector("textarea");

submitBtn.addEventListener('click', (e)=>{
    e.preventDefault();
    const formData = new FormData(document.querySelector("form"));
    console.log(formData)
    paging(submitUrl,"post" ,replaceArea, formData);
    emptyForm();
})
function emptyForm(){
    formName.value = "";
    formTextarea.value="";
}