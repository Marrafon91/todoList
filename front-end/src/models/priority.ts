export type Priority = 'LOW' | 'MEDIUM' | 'HIGH';

export type PrioritySummaryDTO = {
  priority: Priority;
  label: string;
  quantity: number;
};
