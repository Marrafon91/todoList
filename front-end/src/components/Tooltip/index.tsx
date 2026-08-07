import * as Tooltip from '@radix-ui/react-tooltip';
import type { ReactNode } from 'react';
import './style.css';

type Props = {
  children: ReactNode;
  content: string;
  disabled?: boolean;
};

export default function AppTooltip({
  children,
  content,
  disabled = false,
}: Props) {
  if (disabled) {
    return <>{children}</>;
  }

  return (
    <Tooltip.Provider delayDuration={200}>
      <Tooltip.Root>
        <Tooltip.Trigger asChild>{children}</Tooltip.Trigger>

        <Tooltip.Portal>
          <Tooltip.Content
            side="right"
            sideOffset={8}
            className="tooltip-content"
          >
            {content}
            <Tooltip.Arrow className="tooltip-arrow" />
          </Tooltip.Content>
        </Tooltip.Portal>
      </Tooltip.Root>
    </Tooltip.Provider>
  );
}
