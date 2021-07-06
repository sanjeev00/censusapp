import  { Component } from "react";
import { postMember,putMember } from "../API";

class AddMember extends Component
{
    constructor()
    {
        super()
        this.state ={
            firstName:"",
            lastName:"",
            middleName:"",
            dateOfBirth:"",
            gender:"",    
            errors:{},
        }
    }


    componentDidUpdate(prevProps)
    {
        if(prevProps!=this.props)
        {
            if(Object.keys(this.props.currentMember).length === 0)
            {
                this.cleanFields()
            }
            else
            this.setState({...this.props.currentMember,errors:{}})
            console.log(this.props.currentMember.dateOfBirth)

            this.setState({edit:this.props.edit})

        }
    }

    handleChange = (event,field)=>{
       var state = this.state
       state[field] = event.target.value
       this.setState(state)
    }


    genderClicked =(e)=>{
        this.setState({gender:e.target.value})
    }


    AddMember =(event)=>{
        
        var {firstName,lastName,middleName,gender,dateOfBirth} = this.state
        var body = {firstName,lastName,middleName,gender}
        if(dateOfBirth!="")
        {
            body.dateOfBirth = dateOfBirth;
        }

        postMember(body).then(data=>{
            console.log(data)
            this.cleanFields();
            this.props.newMember(data)
            localStorage.setItem("applicationId",data.application.applicationId)
        }).catch(err=>{
            console.log(err)
            this.setState({errors:err})
        })
    }


    cleanFields=()=>{
        this.setState({
            firstName:"",
            lastName:"",
            middleName:"",
            gender:"",
            dateOfBirth:"",
            errors:{},
            memberId:"",
        })
    }

    editMember = ()=>{
        var {firstName,lastName,middleName,gender,dateOfBirth,memberId} = this.state
        var body = {firstName,lastName,middleName,gender,memberId}
        if(dateOfBirth!="")
        {
            body.dateOfBirth = dateOfBirth;
        }
        putMember(body).then(data=>{
            this.cleanFields()
            this.props.updateMember(data,this.props.memberIndex)
            localStorage.setItem("applicationId",data.application.applicationId)
        }).catch(err=>{
            this.setState({errors:err})
        })  
       
    }
    
    
    
    render()
    {
        var dob = ""
        if(this.state.dateOfBirth!="")
         dob =new Date(this.state.dateOfBirth).toISOString().slice(0, 10);


        return(
        <>    
        <h1>
            Add Member
        </h1>
            <form action="member" method="post">
            <div className="row">
                    <div className="col">
                        First Name<input type="text" value={this.state.firstName} onChange={(e)=>{this.handleChange(e,"firstName")}} className="form-control" />
                    <div class="invalid-feedback error">
                    {this.state.errors.firstName}
                    </div>
                    </div>
                   
                    <div className="col">
                        Middle Name<input type="text" value={this.state.middleName}  onChange={(e)=>{this.handleChange(e,"middleName")}} className="form-control" />
                    <div  class="invalid-feedback error">
                        {this.state.errors.middleName}
                    </div>
                    </div>
                   
                    <div className="col">
                        Last Name<input type="text" value={this.state.lastName}   onChange={(e)=>{this.handleChange(e,"lastName")}} className="form-control" />
                        <div  class="invalid-feedback error">
                        {this.state.errors.lastName}
                    </div>
                    </div>

                    <div className="col">
                        Date of Birth
                        <input type="date"  defaultValue={dob} onChange={(e)=>{this.handleChange(e,"dateOfBirth")}} className="form-control" />
                        <div  class="invalid-feedback error">
                        {this.state.errors.dateOfBirth}
                        </div>
                       
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        <div className="d-flex flex-column">
                            Gender
                            <div class="d-flex ">
                                <div class="option"><input type="radio" className="form-check-input" name="gender" checked={this.state.gender==="male"}  value="male" onChange={this.genderClicked}/>male</div>
                                <div class="option"><input type="radio" className="form-check-input" name="gender"  checked={this.state.gender==="female"} value="female"  onChange={this.genderClicked}/>female</div>
                            </div>
                        <div  class="invalid-feedback error">
                            {this.state.errors.gender}
                        </div>
                        </div>
                    </div>
                </div>
                <div className="row">
                <div className="col-12">
                <div  class="invalid-feedback error">
                    {this.state.errors.member}
                </div>
                    { !this.state.edit &&
                    <button class="btn btn-success add-member float-end" type="button" onClick={this.AddMember}>Add Member</button>
                    }
                    {this.state.edit && 
                      <button class="btn btn-success add-member float-end" type="button" onClick={this.editMember}>Edit Member</button>
                    }
                </div>    
                </div>
            </form>
           
            </>
        )
    }
}

export default AddMember