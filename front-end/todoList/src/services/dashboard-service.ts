import { api } from "../utils/api";

export function findDashboard() {
  return api.get("/api/dashboard");
}
