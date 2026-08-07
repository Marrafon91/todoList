import { Link } from 'react-router-dom';

import './style.css';

export default function NotFound() {
  return (
    <main className="not-found">
      <div className="not-found-content">
        <h1>404</h1>

        <h2>Página não encontrada</h2>

        <p>A rota que você tentou acessar não existe.</p>

        <Link to="/" className="not-found-button">
          Voltar para o início
        </Link>
      </div>
    </main>
  );
}
