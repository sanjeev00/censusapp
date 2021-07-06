const host = 'http://localhost:9000'

const  handleResponse =  (res)=>{
    return new Promise(async (resolve,reject)=>{
    let data = await res.json()
    if(res.status==400){
        reject(data)
    }
    else{
        resolve(data)
    }

})
}

export const fetchRelations=(memberId)=>{
    return fetch(`${host}/relation/${memberId}`,{
        method:"GET",
        headers: {
            'Content-Type': 'application/json',
        }
    }).then(handleResponse)
}

export const fetchMembers=()=>{
    return fetch(`${host}/member`,{
        method:"GET",
        headers: {
            'Content-Type': 'application/json',
            'application_id':localStorage.getItem('applicationId')
        }
        
    }).then(handleResponse)

}

export const writeRelations=(body)=>{
    return fetch(`${host}/relation`,{
            method:"POST",
            body:JSON.stringify(body),
            headers: {
                'Content-Type': 'application/json',
                'application_id':localStorage.getItem('applicationId')
            }
            
        }).then(handleResponse)
}


export const postMember=(body)=>{
    return  fetch(`${host}/member`,{
            method:"POST",
            body:JSON.stringify(body),
            headers: {
                'Content-Type': 'application/json',
                'application_id':localStorage.getItem('applicationId')
            }
            
        }).then(handleResponse)
}

export const putMember=(body)=>{
    return fetch("http://localhost:9000/member",{
        method:"PUT",
        body:JSON.stringify(body),
        headers: {
            'Content-Type': 'application/json',
            'application_id':localStorage.getItem('applicationId')
        }
        
    }).then(handleResponse)
}