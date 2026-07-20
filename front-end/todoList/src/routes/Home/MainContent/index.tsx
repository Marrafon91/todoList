import { useEffect, useState } from 'react';

import HeaderContent from '../../../components/HeaderContent';
import DashboardCards from '../../../components/DashboardCards';

import type { DashboardDTO } from '../../../models/dashboard';
import { findDashboard } from '../../../services/dashboard-service';

export default function MainContent() {
  const [dashboard, setDashboard] = useState<DashboardDTO>();

  useEffect(() => {
    async function loadDashboard() {
      try {
        const response = await findDashboard();

        setDashboard(response.data);
      } catch (error) {
        console.log(error);
      }
    }

    loadDashboard();
  }, []);

  if (!dashboard) {
    return <p className="error-message">Carregando... Espedando resposta do Backend.</p>;
  }

  return (
    <>
      <HeaderContent dashboard={dashboard} />
      <DashboardCards dashboard={dashboard} />
    </>
  );
}
