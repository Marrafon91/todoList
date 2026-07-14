import { api } from "../utils/api";

export function findAllTasks() {
  return api.get("/api/tasks");
}
