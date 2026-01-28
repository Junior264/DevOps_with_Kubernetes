import './App.css';
import { useState, useEffect } from 'react';

const App = () => {
  const [version, setVersion] = useState(() => Date.now());
  const [todoText, setTodoText] = useState("");
  const [todos, setTodos] = useState([]);
  const FETCH_INTERVAL = Number(import.meta.env.VITE_FETCH_INTERVAL) || 600000;

  const fetchTodos = () => {
    fetch("/todos")
      .then(res => {
        if (!res.ok) throw new Error("Failed to fetch todos");
        return res.json();
      })
      .then(data => setTodos(data))
      .catch(err => console.error(err));
  };

  const updateImageVersion = () => {
    setVersion(Date.now());
  };

  const requestNewImage = () => {
    fetch("/image/create")
      .then(() => {
        setTimeout(updateImageVersion, 1000);
      });
  };

  const handleTimerTick = () => {
    requestNewImage();
  };

  useEffect(() => {
    fetchTodos();
    const intervalId = setInterval(handleTimerTick, FETCH_INTERVAL);

    return () => clearInterval(intervalId);
  }, []);

  const handleInputChange = (e) => {
    const text = e.target.value;
    if (text.length <= 140) {
      setTodoText(text);
    }
  };

  const handleSendTodo = () => {
    if (todoText.trim().length > 0) {
      fetch("/todos", {
        method: "POST",
        headers: { "Content-Type": "text/plain" }, 
        body: todoText
      })
      .then(res => {
        if (res.ok) {
          setTodoText("");
          fetchTodos();
        }
      })
      .catch(err => console.error("Error sending todo:", err));
    }
  };

  return (
      <div>
    <h1>Welcome to the frontend :)</h1>
    <img  src={`/images/randomImage.jpg?v=${version}`} onError={requestNewImage} />
    <div style={{ display: 'flex', gap: '10px', marginBottom: '5px' }}>
      <input 
        type="text" 
        value={todoText} 
        onChange={handleInputChange} 
        placeholder="Add a todo"
        style={{
          flex: 1,
          padding: '10px',
          borderRadius: '5px',
          border: '1px solid #ccc',
          outline: 'none',
          marginTop: '100px'
        }}
      />
      <button 
        onClick={handleSendTodo}
        style={{
          padding: '10px 20px',
          backgroundColor: '#007bff',
          color: 'white',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer',
          fontWeight: 'bold',
          marginTop: '100px'
        }}
      >
        Send
      </button>
    </div>
    
    <div style={{textAlign: 'left',fontSize: '12px', color: todoText.length >= 140 ? 'red' : '#666', marginBottom: '20px'}}>
      {todoText.length}/140 characters
    </div>

    <ul>
      {todos.map((todo) => (
        <li key={todo.id} style={{padding: '10px 0', display: 'flex', alignItems: 'center'}}> {todo.name} </li>
      ))}
    </ul>
  </div>
  );
};

export default App;