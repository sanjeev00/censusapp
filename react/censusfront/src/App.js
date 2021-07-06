import logo from './logo.svg';
import './App.css';
import CreateApplication from './Components/CreateApplication';
import SearchApplication from './Components/SearchApplication';
import SelectRelation from './Components/SelectRelation';
import Home from './Components/Home';
import Success from './Components/Success';
import {
  BrowserRouter as Router,
  Switch,Route
} from "react-router-dom";

function App() {
  return (
    <div className="container-fluid">
    <div className="row">
    <div className="col col-10 mx-auto">
      <Router>
      <Switch>
        <Route path='/create'><CreateApplication/></Route>
        <Route path='/relation'><SelectRelation/></Route>
        <Route path='/search'><SearchApplication/></Route>
        <Route path='/success'><Success/></Route>
        <Route path='/'><Home/></Route>
      </Switch>
      </Router>
      
    </div>
    </div>
    </div>
  );
}

export default App;
