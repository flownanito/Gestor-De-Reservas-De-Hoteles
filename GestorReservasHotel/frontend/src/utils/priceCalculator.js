export const calculateReservationPrice = (checkIn, checkOut, basePrice) => {
    const toDate = (s) => (s ? new Date(`${s}T00:00:00`) : null);
    const inD = toDate(checkIn);
    const outD = toDate(checkOut);

    let nights = 0;
    if (inD && outD && outD > inD) {
        nights = Math.ceil((outD - inD) / (1000 * 60 * 60 * 24));
    }

    const pricePerNight = Number(basePrice ?? 0);
    const subtotal = nights * pricePerNight;

    const taxes = Math.round(subtotal * 0.1 * 100) / 100;
    const total = Math.round((subtotal + taxes) * 100) / 100;

    return { nights, subtotal, impuestos: taxes, total };
};
