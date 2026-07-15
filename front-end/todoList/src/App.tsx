import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './routes/Home';
import MainContent from './routes/Home/MainContent';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />}>
          <Route index element={<MainContent />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
