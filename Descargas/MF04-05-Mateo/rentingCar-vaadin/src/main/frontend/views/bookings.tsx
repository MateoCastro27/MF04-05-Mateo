import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useEffect, useState } from 'react';
import { BookingEndpoint } from 'Frontend/generated/endpoints';
import Booking from 'Frontend/generated/dev/app/rentingcartestvaadin/model/Booking';



export const config: ViewConfig = {
  menu: { order: 2, icon: 'line-awesome/svg/address-book-solid.svg' },
  title: 'Bookings',
};

export default function BookingsView() {
    const [bookings, setBookings] = useState<Booking[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchBookings = async () => {
          try {
            setLoading(true);
            const bookingData = await BookingEndpoint.getAllBookings();
            setBookings(bookingData ? Array.from(bookingData).filter((booking): booking is Booking => booking !== undefined) : []);
          } catch (err) {
            setError('Failed to fetch cars: ' + (err as Error).message);
          } finally {
            setLoading(false);
          }
        };

        fetchBookings();
    }, []);

    if (loading) {
        return (
          <div className="flex flex-col h-full items-center justify-center p-4">
            <h1>Bookings</h1>
            <p>Loading bookings...</p>
          </div>
        );
    }

    if (error) {
        return (
          <div className="flex flex-col h-full items-center justify-center p-4">
            <h1>Bookings</h1>
            <p className="text-red-500">{error}</p>
          </div>
        );
    }
  return (
    <div className="flex flex-col h-full p-4">
      <h1 className="text-2xl font-bold mb-4">Bookings ({bookings.length})</h1>
      <br/>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {bookings.map((booking) => (
          <div key={booking.id} style={{ border: '1px solid #ccc', borderRadius: '8px', padding: '16px'}}>
            <h3 className="text-lg font-semibold">Booking ID: {booking.id}</h3>
            <div className="mt-2 space-y-1">
              <p><strong>Customer:</strong> {booking.client?.name} {booking.client?.lastName}</p>
              <p><strong>Car:</strong> {booking.car?.brand} {booking.car?.model}</p>
              <p><strong>Booking Date:</strong>  {new Date(booking.bookingDate * 86400000).toLocaleDateString()}</p>
              <p><strong>QtyDays:</strong> {booking.qtyDays}</p>
              <p><strong>Total Price:</strong> ${booking.totalAmount}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}