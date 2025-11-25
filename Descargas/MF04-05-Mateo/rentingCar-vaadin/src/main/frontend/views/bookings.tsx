import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useEffect, useState } from 'react';
import { BookingEndpoint } from 'Frontend/generated/endpoints';
import Booking from 'Frontend/generated/dev/app/rentingcartestvaadin/model/Booking';

export const config: ViewConfig = {
  menu: { order: 2, icon: 'line-awesome/svg/calendar-check-solid.svg' },
  title: 'Bookings'
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
        setBookings(data ? Array.from(data).filter((b): b is Booking => b !== undefined) : []);
      } catch (err) {
        setError('Failed to fetch bookings: ' + (err as Error).message);
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

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {bookings.map((booking) => {
          // Calculamos la fecha de fin a partir de bookingDate + qtyDays
          const endDate = new Date(booking.bookingDate);
          endDate.setDate(endDate.getDate() + booking.qtyDays);

          return (
            <div key={booking.id} className="border rounded-lg p-4 shadow-md">
              <h3 className="text-lg font-semibold">
                {booking.car?.brand} {booking.car?.model} ({booking.car?.plate})
              </h3>
              <div className="mt-2 space-y-1">
                <p><strong>Cliente:</strong> {booking.client?.name} {booking.client?.lastName}</p>
                <p><strong>Fecha inicio:</strong> {new Date(booking.bookingDate).toLocaleDateString()}</p>
                <p><strong>Fecha fin:</strong> {endDate.toLocaleDateString()}</p>
                <p><strong>Días:</strong> {booking.qtyDays}</p>
                <p><strong>Precio total:</strong> €{booking.totalAmount}</p>
                <p>
                  <strong>Estado:</strong>{' '}
                  <span className={booking.active ? 'text-green-600' : 'text-red-600'}>
                    {booking.active ? 'Activa' : 'Cancelada'}
                  </span>
                </p>
              </div>
            </div>
          );
        })}
      </div>

      {bookings.length === 0 && (
        <div className="text-center mt-8">
          <p>No bookings found. Try populating the database first.</p>
        </div>
      )}
    </div>
  );
}