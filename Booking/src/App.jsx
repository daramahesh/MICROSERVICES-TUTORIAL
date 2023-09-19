import {
  createBrowserRouter,
  Outlet,
  RouterProvider,
} from "react-router-dom";
import Home from "./pages/home/Home";
import Navbar from "./components/navbar/Navbar";
import Footer from "./components/footer/Footer";
import List from "./pages/list/List";
import Hotel from "./pages/hotel/Hotel";

function App() {

  const Layout = () =>{
    return (
    <div className="app">
        <Navbar />
        <Outlet />
        <Footer />
      </div>
    )
  }

  const router = createBrowserRouter([
    {
      path: "/",
      element: <Layout />,
      children: [
        {
          path: "/",
          element: <Home />
        },
        {
          path: "/hotels",
          element: <List />
        },
        {
          path: "/hotel/:id",
          element: <Hotel />
        }
      ]
    },
  ]);

  return (
    <RouterProvider router={router} />
  )
}

export default App
