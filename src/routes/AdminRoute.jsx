import { Navigate } from "react-router-dom";
import { getToken, getRole } from "../utils/auth";

export default function AdminRoute({ children }) {
  const token = getToken();
  const role = getRole();

  if (!token) return <Navigate to="/login" replace />;

  if (role !== "ADMIN") return <Navigate to="/login" replace />;

  return children;
}