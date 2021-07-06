import { Component } from "react";
import { fetchMembers } from "../API";
import AddMember from  "./AddMember"

class CreateApplication extends Component
{

    constructor()
    {
        super()
        this.state={
            members:[],
            currentMember:{},
            edit:false,
        }
    }

    componentDidMount()
    {
        fetchMembers().then(data=>{
            this.setState({members:data})
            }).catch(err=>{
            this.setState({errors:err})
        })
       
    }

    //used for selecting a member to edit so  UI updates
    selectMember = (i)=>{
        this.setState({ currentMember: this.state.members[i], edit: true, memberId:i })
    }

    // used for adding member details
    newMember = (data)=>{
        var members = this.state.members.slice()
        members.push(data)
        this.setState({members})
    }
    
    // used for editing member details
    updateMember =(data,index)=>{
        var members = this.state.members.slice()
        members[index] = data
        console.log(members)
        this.setState({members})
        this.resetMember()
    }

    // used for removed any selections and moving to add member screen
    resetMember =()=>{
        this.setState({currentMember:{},edit:false})
    }

    render()
    {
        let memberList  =[]
        this.state.members.forEach((member,index)=>{
            memberList.push(<div className={this.state.currentMember.memberId==member.memberId?"memberitem activeMember":"memberitem"}  onClick={()=>this.selectMember(index)}>{member.firstName}</div>)
        })


        return(
        <div className="row">
       <div className="col-12 col-md-3 sidecard">
           <div><h6>List of members</h6></div>
            {memberList}
            <div className={!this.state.edit?"memberitem activeMember":"memberitem"} onClick={()=>{this.resetMember()}}>+ Add New Member</div>
       </div>
        <div className="col-12 col-md-9 maincard">
            <AddMember currentMember={this.state.currentMember} updateMember={this.updateMember} newMember={this.newMember} edit={this.state.edit} memberIndex={this.state.memberId}/>
            <div className="col-12">
                <button className="btn btn-success" onClick={()=>window.close('','_parent','')}>Save and Exit</button>
                <a className="btn btn-success float-end" href='relation'>Next</a>
                    <button className="btn btn-success float-end">Back</button>
            </div>
        </div>
        </div>
        )
    }
}


export default CreateApplication
