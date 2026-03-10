export const getToken = () => {
  return localStorage.getItem("token");
};

export const getRole = () => {
  const token = getToken();
  if (!token) return null;

  try {
    const payload = JSON.parse(atob(token.split(".")[1]));
    return payload.role; // ADMIN / USER
  } catch {
    return null;
  }
};

export const logout = () => {
  localStorage.removeItem("token");
};