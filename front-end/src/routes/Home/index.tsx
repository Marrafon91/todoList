import { Outlet } from 'react-router-dom';
import Sidebar from '../../components/SideBar';

import './style.css';

export default function Home() {
  return (
    <div className="app-container">
      <Sidebar />
      <Outlet />
    </div>
  );
}
