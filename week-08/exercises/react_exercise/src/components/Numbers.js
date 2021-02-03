import React from 'react';

function Numbers(props) {
    const numbersToDisplay = [];
    let startNumber = props.min;

    while (startNumber < props.max) {
        numbersToDisplay.push(startNumber);
        startNumber++;
    }
    console.log(props.min);
    console.log(props.max);

    const listItems = numbersToDisplay.map((number) =>
        <li>{number}</li>);
    return (
        <ul>
            {listItems}
        </ul>
    );
}

export default Numbers;