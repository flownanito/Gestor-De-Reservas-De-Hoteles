const API_URL = "http://localhost:8080/api/reservations";

export async function createReservation(payload) {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });

  if (!res.ok) {
    const text = await res.text().catch(() => "");
    throw new Error(`Error creating reservation: ${res.status} ${text}`);
  }

  return res.json();
}