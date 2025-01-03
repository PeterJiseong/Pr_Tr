


let paging = function pagingFunction(url,method,area, formData){
    if(method === "get"){
        fetch(url)
            .then((response)=>{
                return response.text();
            })
            .then((data)=>{
                area.innerHTML=data;
            })
    }else{

        let options = {
            method: 'POST',
            cache: 'no-cache',
            body: formData
        }
        fetch(url, options)
            .then((response)=>{
                return response.text();
            })
            .then((data)=>{
                area.innerHTML=data;
            })
    }
}

export default paging;

