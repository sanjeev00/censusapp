import { Component } from "react";
import { fetchMembers, fetchRelations, writeRelations } from "../API";

class SelectRelation extends Component
{

    constructor(props)
    {
        super(props)
        this.state={
            members:[],
            allRelations:[],
        }
       
    }



    componentDidMount()
    {
        fetchMembers().then(data=>{
            this.setState({members:data})
            data.forEach(member=>{
                if(member.isHead)
                {
                    this.getRelations(member.memberId)
                    this.setState({head:member.memberId})
                }
            })
            let allRelations=[]    
            data.forEach(member=>{
            if(!member.isHead)
                {
                    allRelations.push({
                        firstMember: { memberId: this.state.head },
                        secondMember:member,
                        relationship:"",
                    })
                }
            })
            this.setState({allRelations})
        }).catch(err=>{
            this.setState({errors:err})
        })
    }

    getRelations = (memberId) => {
        fetchRelations(memberId).then(data => {
            if (data.length > 0) 
                this.setState({allRelations: data})
            
        }).catch(err => {
            this.setState({errors: err})
        })
    }



    addRelation = (memberId, relationship) => {
        let allRelations = this.state.allRelations;
        allRelations[memberId].relationship = relationship;
        this.setState({allRelations})
    }



    submitRelations =()=>{
        let relations = []
        relations = this.state.allRelations.map(relation => {
            return {
                ...relation,
                secondMember: {
                    memberId: relation.secondMember.memberId
                },
                firstMember: {
                    memberId: relation.firstMember.memberId
                }
            }
        })
        writeRelations(relations).then(data => {
            this.setState({allRelations: data})
            console.log(data)
        }).catch(err => {
            this.setState({errors: err})
        })

    }

    render()
    {
        let relations = []
         this.state.allRelations.forEach((relation,ind)=>{
                relations.push(
                    <Relation firstName={relation.secondMember.firstName} 
                    relationship={relation.relationship}
                    memberId={relation.secondMember.memberId} index={ind} addRelation={this.addRelation} />
                )
            })
           
        return(
            <div className="row">
            <div className="col-12 col-md-3 sidecard">
                

            </div>
             <div className="col-12 col-md-9 maincard">
                <h2>
                HouseHold Relationship
                </h2>
                Tell us how the members of your HouseHold are related to each other
                 <div className="col-12">
                 <div class="col-md-8">
                    {relations}
                 </div>
                     <button className="btn btn-success">Save and Exit</button>
                     <button className="btn btn-success float-end"  onClick={this.submitRelations}>Submit</button>
                         <button className="btn btn-success float-end">Back</button>
                 </div>
             </div>
             </div>
        )
    }
}



class Relation extends Component
{
    constructor(props)
    {
        super(props)
        this.state={
            relationship:""
        }
       
    }

    
    componentDidUpdate(prevProps)
    {
        if(this.props!=prevProps)
        {
            this.setState({relationship:this.props.relationship})
        }
    }

    onRelationSelect = (event)=>{
        this.setState({relationship:event.target.value},()=>{
            this.props.addRelation(this.props.index,this.state.relationship)
        })
    }

    render()
    {
        return(
            <div class='row'>
                <div class="col-md-4">
                Relation to {this.props.firstName}
                </div>
                <div class="col-md-5 col-xs-12">
                <select name="member2" class="form-select"  value={this.state.relationship} onChange={this.onRelationSelect}>
                    <option value="father">Father</option>
                    <option value="mother">Mother</option>
                    <option value="son">Son</option>
                    <option value="daughter">Daughter</option>
                </select>
                </div>
            </div>
        
        )
    }
}

export default SelectRelation