import { Outlet } from 'react-router-dom';
import HeaderContent from '../../components/HeaderContent';
import SideBar from '../../components/SideBar';

export default function Home() {
  return (
    <>
      <HeaderContent />
      <SideBar />
      <Outlet />
    </>
  );
}
