import Heading from './components/Heading.js';
import Numbers from './components/Numbers';
import React, { useState } from 'react';

function App() {

  const initalHeaderData = [
    {
      id: 1,
      title: 'Big',
      body: 'Jake'
    },
    {
      id: 2,
      title: 'Little',
      body: 'Morgan'
    }
  ];

  const [heading, setHeading] = useState(initalHeaderData);


  return (
    <div className="App">

      {
        heading.map(header => (
          <Heading key={header.id} header={header} />
        ))
      }
      <div>
        <Numbers
          min={1}
          max={11}
        />
      </div>
    </div>
  );
}

export default App;
