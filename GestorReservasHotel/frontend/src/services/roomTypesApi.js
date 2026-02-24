const API_URL = "http://localhost:8080/api/room-types";

export async function fetchRoomTypes() {
    const res = await fetch(API_URL);

    if (!res.ok) {
        throw new Error(`Error fetching room types: ${res.status}`);
    }

    return res.json();
}