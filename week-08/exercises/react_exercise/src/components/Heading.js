import React from 'react';

function Heading({header}) {

    const { title, body } = header;

    return (
        <h2>{title} {body}</h2>
    );
}

export default Heading;