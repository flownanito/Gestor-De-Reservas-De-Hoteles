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

export async function generateInvoice(reservationId) {
  const res = await fetch(`${API_URL}/${reservationId}/invoice`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
  });

  if (!res.ok) {
    const text = await res.text().catch(() => "");
    throw new Error(`Error invoicing reservation: ${res.status} ${text}`);
  }

  return res.json();
}

export async function getReservationsByClient(clientId) {
  const res = await fetch(`${API_URL}/client/${clientId}`);
  if (!res.ok) {
    const text = await res.text().catch(() => "");
    throw new Error(`Error fetching reservations: ${res.status} ${text}`);
  }
  return res.json();
}

export async function getInvoiceByReservation(reservationId) {
  const res = await fetch(`${API_URL}/${reservationId}/invoice`);
  if (!res.ok) {
    const text = await res.text().catch(() => "");
    throw new Error(`Error fetching invoice: ${res.status} ${text}`);
  }
  return res.json();
}