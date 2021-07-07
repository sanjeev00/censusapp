import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';

function App() {
  let [search,setSearch] = useState("")
  let [error,setError] = useState("")
  let [movies,setMovies] = useState([])
  let [favorites,setFavorites] = useState({})
  let [details,setDetails] = useState({})
  function getMovies()
  {
      if(search.length<3){   
        setError("Minimum 3 characters required for search")
        setMovies([])
        return 
      }

      fetch("https://www.omdbapi.com/?apikey=5d056ffc&s="+search)
      .then(res=>res.json())
      .then(data=>{
        setMovies(data.Search)
      })

  } 

  let addMovie=(data)=>{
    let fav ={...favorites}
    if(Object.keys(fav).length<10){
    fav[data.imdbID] = data
    setFavorites(fav)
    }
    else{
      setError("Cannot add more than 10 movies")
    }

  }

  let removeMovie=(data)=>{    
    let fav ={...favorites}
    delete fav[data.imdbID]
    setFavorites(fav)
  }
  
  let getDetails=(id)=>{
      
    fetch("https://www.omdbapi.com/?apikey=5d056ffc&i="+id)
    .then(res=>res.json())
    .then(data=>{
      setDetails(data)
    })
  }

  
  return (
    <div>
      <input type="text" value={search} onChange={(e)=>setSearch(e.target.value)} />
      <button onClick={getMovies}>
        Search
      </button>
      <p style={{color:'red'}}>{error}</p>
      <div  style={{display:'flex',flexDirection:'row'}}>
        <div  style={{display:'flex',flexDirection:'column'}}>
        <MovieList movies={movies} addMovie={addMovie} removeMovie={removeMovie} favorites={favorites}></MovieList>
        </div>
        <div  style={{display:'flex',flexDirection:'column'}}>
        <h1>Favorites</h1>
        {Object.keys(favorites).map((movieId)=>{
         return <FavoriteCard key={movieId} movie={favorites[movieId]} removeMovie={removeMovie} getDetails={getDetails}/>
        })}
        </div>
        <DetailView details={details}></DetailView>
        </div>
    </div>
      
    
  );
}


function DetailView(props)
{
  if(Object.keys(props.details).length!=0)
  {
  return(
     <div  style={{display:'flex'}} > 
    <img src={props.details.Poster} alt="movie poster" height='450px'/>
    <div style={{display:'flex',flexDirection:'column'}}>
    <h2>Actors: {props.details.Actors}</h2>
    <h4>Awards: {props.details.Awards}</h4>
    <h3>IMDB Rating:{props.details.imdbRating}</h3>
    <p>{props.details.Plot}</p>
    </div>
    </div>
  )}
  else return(null)
}



function FavoriteCard(props)
{

  let removeMovie=()=>{
    props.removeMovie(props.movie)
  }
  let getDetails=()=>{
    props.getDetails(props.movie.imdbID)
  }
  
  return(
    <div style={{display:'flex'}} className="moviecard"  onClick={getDetails}>
      <img src={props.movie.Poster} height="150px"></img>
      <div style={{display:'flex',flexDirection:'column'}}>
      <h2>{props.movie.Title}</h2>
      {props.movie.Year}
      <button onClick={removeMovie}>Remove</button>
      </div>
     
    </div>
  )
}



function MovieList(props)
{
  console.log(props)
  return(
    props.movies.map(movie=>{
      var added = false
      if(movie.imdbID in props.favorites)
      added = true
      return <MovieCard key={movie.imdbID} movie={movie} added={added} addMovie={props.addMovie} removeMovie={props.removeMovie} />
    })
  )
}


function MovieCard(props)
{
  const [added,setAdded] =useState(props.added)
  let addMovie=()=>{
    props.addMovie(props.movie)  
  }

  let removeMovie=()=>{
    
    props.removeMovie(props.movie)
  }
  useEffect(()=>{setAdded(props.added)},[props.added])

  return(
    <div style={{display:'flex'}} >
    <img src={props.movie.Poster} height="150px"></img>
    <div style={{display:'flex',flexDirection:'column'}}>
    <p>{props.movie.Title}</p>
    {props.movie.Year}
    {!added?<button onClick={addMovie}>Add</button>:
      <button onClick={removeMovie}>Remove</button>}
    </div>
  </div>

    )
}






export default App;
