import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useEffect, useState } from 'react';
import { BookingEndpoint } from 'Frontend/generated/endpoints';
import type Booking from 'Frontend/generated/dev/app/rentingcartestvaadin/model/Booking';

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
        const data = await BookingEndpoint.getAllBookings();
        console.log('Bookings from backend:', data); // <-- depuración
        const filteredData = data ? data.filter((b): b is Booking => b !== undefined) : [];
        setBookings(filteredData);
      } catch (err) {
        setError('Failed to fetch bookings: ' + (err as Error).message);
      } finally {
        setLoading(false);
      }
    };

    fetchBookings();
  }, []);

  // Loader mientras se obtienen los bookings
  if (loading) {
    return (
      <div className="flex flex-col h-full items-center justify-center p-4">
        <h1 className="text-2xl font-bold mb-2">Bookings</h1>
        <p>Loading bookings...</p>
      </div>
    );
  }

  // Mostrar error si ocurre
  if (error) {
    return (
      <div className="flex flex-col h-full items-center justify-center p-4">
        <h1 className="text-2xl font-bold mb-2">Bookings</h1>
        <p className="text-red-500">{error}</p>
      </div>
    );
  }

  // Mensaje si no hay bookings
  if (!bookings.length) {
    return (
      <div className="flex flex-col h-full items-center justify-center p-4">
        <h1 className="text-2xl font-bold mb-2">Bookings</h1>
        <p>No bookings found. Make sure the database is populated.</p>
        <pre className="mt-4">{JSON.stringify(bookings, null, 2)}</pre> {/* Depuración */}
      </div>
    );
  }

  // Render del listado de bookings
  return (
    <div className="flex flex-col h-full p-4">
      <h1 className="text-2xl font-bold mb-4">Bookings ({bookings.length})</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {bookings.map((booking) => (
          <div key={booking.id} className="border rounded-lg p-4 shadow-md">
            <h3 className="text-lg font-semibold">
              {booking.car ? `${booking.car.brand} ${booking.car.model}` : 'Unknown Car'}
            </h3>
            <div className="mt-2 space-y-1">
              <p>
                <strong>Client:</strong>{' '}
                {booking.client ? `${booking.client.name} ${booking.client.lastName}` : 'Unknown Client'}
              </p>
              <p>
                <strong>Booking Date:</strong>{' '}
                {booking.bookingDate
                  ? new Date(booking.bookingDate * 1000).toLocaleDateString()
                  : 'Unknown'}
              </p>
              <p><strong>Days:</strong> {booking.qtyDays}</p>
              <p><strong>Total Amount:</strong> €{booking.totalAmount.toFixed(2)}</p>
              <p><strong>Status:</strong> {booking.active ? 'Active' : 'Inactive'}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
