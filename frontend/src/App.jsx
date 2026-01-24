import './App.css';
import { useState, useEffect } from 'react';

const App = () => {
  const [version, setVersion] = useState(() => Date.now());

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
    const intervalId = setInterval(handleTimerTick, 10 * 60 * 1000);

    return () => clearInterval(intervalId);
  }, []);

  return (
    <div>
      <h1>Welcome to the frontend :)</h1>
      
      <img 
        src={`/images/randomImage.jpg?v=${version}`} 
        onError={requestNewImage} 
        alt="Hourly Random"
      />
    </div>
  );
};

export default App;