const solo=[];
const addSolo = ({ id, name,room}) => {
    name = name.trim().toLowerCase();

    const existingUser = solo.find((user) => user.room === room && user.name === name);

    if(!name || !room) return { error: 'Username and room are required.' };
    if(existingUser) return { error: 'Username is taken.' };

    const user = { id, name, room };
    solo.push(user);
    console.log(solo)
    return { user };
}
const removeSoloUser = (id) => {
    const index = solo.findIndex((user) => user.id === id);
    if(index !== -1) return solo.splice(index, 1)[0];
}
const getSoloUser = (id) => {
   return  solo.find((user) => user.id===id);
   // solo.forEach((user)=>console.log(user.id===id))
}
const getSoloInRoom = (room) => solo.filter((user) => user.room === room);
module.exports = { addSolo,getSoloInRoom,removeSoloUser ,getSoloUser};