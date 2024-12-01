function kakaoAuthorize(e){
    const authorizeUrl = "/member/kakaoAuthorize";
    fetch(authorizeUrl)
        .then((res)=>{
            console.log(res);
            return res.text();
        })
        .then((data)=>{
            console.log(data)
        })
}