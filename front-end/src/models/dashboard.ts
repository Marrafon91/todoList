export type DashboardCardDTO = {
  title: string;
  value: number;
};

export type DashboardDTO = {
  greeting: string;
  currentDate: string;
  pendingTasks: number;
  highPriorityTasks: number;
  cards: DashboardCardDTO[];
};
