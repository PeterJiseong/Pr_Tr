


let paging = function pagingFunction(url,method,area){
    if(method === "get"){
        fetch(url)
            .then((response)=>{
                return response.text();
            })
            .then((data)=>{
                area.innerHTML=data;
            })
    }
}

export default paging;

