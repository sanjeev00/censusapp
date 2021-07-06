import { Component } from "react";

class Home extends Component
{

    render()
    {
        return(
            <div className="row">
            <div className="col col-3 sidecard">
        
            </div>
            <div className="col col-9 maincard">
                <div className="col-4 d-flex flex-column">
                <a href="create" className="btn btn-success">Create Application</a>
                <a href="search" className="btn btn-success">Search Application</a>
                
            </div>
            </div>
            </div>
        
        )

    }

}

export default Home