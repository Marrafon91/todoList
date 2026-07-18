import './style.css';
import { Outlet } from 'react-router-dom';
import HeaderContent from '../../components/HeaderContent';
import Sidebar from '../../components/SideBar';

export default function Home() {
  return (
    <div className="app-container">
      <Sidebar />

      <main className="main-content">
        <HeaderContent />
        <Outlet />
      </main>
    </div>
  );
}
