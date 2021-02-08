import '../assets/css/Errors.css';

function Errors({ errors }) {
    if (errors.length === 0) {
        return null;
    }

    return (
        
        <div id='errors' class="alert alert-dismissible fade show" role="alert">
            The following errors were found:
                <ul>
                {errors.map(error => (
                    <li id='list' key={error}>{error}</li>
                ))}
            </ul>
        </div>
    )
};

export default Errors;