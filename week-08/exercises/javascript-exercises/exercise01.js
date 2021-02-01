// MANAGER FEATURE REQUESTS
// You have three managers: A, B, and C.
// Occasionally, they ask you to add features to your company software.
// Use if/else statements to enforce the following rules:
// - If all three ask for the feature, print "Feature in progress."
// - If any two of the three ask, print "Adding feature to schedule."
// - If only one of the three ask, print "Going to hold off for a bit."
// - If none of the managers ask, print "Nothing to do..."

const managerAAsked = false;
const managerBAsked = false;
const managerCAsked = false;

if (managerAAsked && managerBAsked && managerCAsked){
    console.log("Feature in progress.")
}

else if (managerAAsked && managerBAsked && !managerCAsked){
    console.log("Adding feature to schedule.")
}

else if (managerAAsked && !managerBAsked && managerCAsked){
    console.log("Adding feature to schedule.")
}

else if (!managerAAsked && managerBAsked && managerCAsked){
    console.log("Adding feature to schedule.")
}

else if (!managerAAsked && !managerBAsked && managerCAsked){
    console.log("Going to hold off for a bit.")
}

else if (!managerAAsked && managerBAsked && !managerCAsked){
    console.log("Going to hold off for a bit.")
}

else if (managerAAsked && !managerBAsked && !managerCAsked){
    console.log("Going to hold off for a bit.")
}

else if (!managerAAsked && !managerBAsked && !managerCAsked){
    console.log("Nothing to do.")
}




// 1. Add decisions statements to cover all scenarios.
// 2. Change manager variables to test all scenarios.