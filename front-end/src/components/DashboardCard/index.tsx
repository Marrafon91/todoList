import './style.css';

type Props = {
  title: string;
  value: number;
  color: string;
};

export default function DashboardCard({ title, value, color }: Props) {
  return (
    <div className="dashboard-card">
      <span className="dashboard-card-title">{title}</span>

      <h2 className="dashboard-card-value" style={{ color }}>
        {value}
      </h2>
    </div>
  );
}
