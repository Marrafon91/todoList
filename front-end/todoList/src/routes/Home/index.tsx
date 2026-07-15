import { Outlet } from 'react-router-dom';
import HeaderContent from '../../components/HeaderContent';

export default function Home() {
  return (
    <>
      <HeaderContent />
      <Outlet />
    </>
  );
}
